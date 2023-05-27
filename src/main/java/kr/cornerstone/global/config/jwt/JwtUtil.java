package kr.cornerstone.global.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import kr.cornerstone.global.config.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.access.token-secret}")
    private String ACCESS_SECRET_KEY;
    @Value("${jwt.refresh.token-secret}")
    private String REFRESH_SECRET_KEY;

    private static final String AUTHORITIES_KEY = "auth";
    private static final String ISSUER = "KBL";
    private static final long ACCESS_TOKEN_VALIDATION_SECOND = 1000L * 60 * 60 * 24; //24시간
    private static final long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 60 * 60 * 24 * 30; //30일

    /*토큰생성*/
    public String createToken(UserPrincipal userPrincipal, TokenType tokenType) {
        /*payload 생성*/
        HashMap<String,Object> claimMap = new HashMap<>();
        Date now = new Date();
        Date expiryDate;

        SignatureAlgorithm algorithm;
        SecretKey secretKey;

        if(tokenType.equals(TokenType.ACCESS_TOKEN)) {
            String authorities = userPrincipal.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(","));

            /*ACCESS_TOKEN*/
            expiryDate = new Date(now.getTime() + ACCESS_TOKEN_VALIDATION_SECOND);
            algorithm = SignatureAlgorithm.HS512;
            secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(ACCESS_SECRET_KEY));
            claimMap.put(AUTHORITIES_KEY, authorities);
        }else{
            /*REFRESH_TOKEN*/
            expiryDate = new Date(now.getTime() + REFRESH_TOKEN_VALIDATION_SECOND);
            algorithm = SignatureAlgorithm.HS512;
            secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(REFRESH_SECRET_KEY));
        }

        /*JWT 생성*/
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .addClaims(claimMap) //페이로드 추가
                .signWith(secretKey, algorithm)
                .setIssuer(ISSUER)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .compact();
    }

    /*토큰 파싱*/
    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        // 토큰 복호화
        Claims claims = parseClaims(token, TokenType.ACCESS_TOKEN);

        if (Objects.requireNonNull(claims).get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

//        List<GrantedAuthority> authorities = Collections.
//                singletonList(new SimpleGrantedAuthority(user.getAuthType().toString()));

        /*토큰검증을 이미 완료 했기 때문에 DB 조회없이 객체 생성해서 반환*/
        UserPrincipal principal = UserPrincipal.builder()
                .id(Long.parseLong(claims.getSubject()))
                .authorities(authorities)
                .build();

        return new UsernamePasswordAuthenticationToken(principal, null, authorities);
    }

    private Claims parseClaims(String token, TokenType tokenType) {
        try {
            if(tokenType == TokenType.ACCESS_TOKEN){
                return Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(ACCESS_SECRET_KEY)))
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
            }
            if(tokenType == TokenType.REFRESH_TOKEN){
                return Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(REFRESH_SECRET_KEY)))
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
            }
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
        return null;
    }

    /*토큰 아이디조회*/
    public String getIdFromToken(String token, TokenType tokenType) {
        Claims claims = parseClaims(token,tokenType);
        return String.valueOf(claims.get("sub"));
    }

    /*엑세스토큰 검증*/
    public boolean validateToken(String token, TokenType tokenType) {
        try {
            if(tokenType == TokenType.ACCESS_TOKEN){
                Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(ACCESS_SECRET_KEY))).build().parseClaimsJws(token);
                return true;
            }
            if(tokenType == TokenType.REFRESH_TOKEN){
                Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(REFRESH_SECRET_KEY))).build().parseClaimsJws(token);
                return true;
            }
        } catch (SecurityException ex) {
            log.error("잘못된 JWT 서명입니다.");
        } catch (MalformedJwtException ex) {
            log.error("유효하지 않은 JWT 입니다.");
        } catch (ExpiredJwtException ex) {
            log.error("만료된 JWT 입니다.");
        } catch (UnsupportedJwtException ex) {
            log.error("지원되지 않는 JWT 입니다.");
        } catch (IllegalArgumentException ex) {
            log.error("JWT 가 잘못되었습니다.");
        }
        return false;
    }


}
