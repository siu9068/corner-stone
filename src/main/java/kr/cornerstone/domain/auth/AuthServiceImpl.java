package kr.cornerstone.domain.auth;

import kr.cornerstone.global.config.jwt.JwtUtil;
import kr.cornerstone.global.config.jwt.TokenType;
import kr.cornerstone.global.config.security.UserPrincipal;
import kr.cornerstone.domain.user.User;
import kr.cornerstone.global.enums.UseType;
import kr.cornerstone.global.payload.AuthResponse;
import kr.cornerstone.domain.auth.payload.RefreshTokenRequest;
import kr.cornerstone.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public AuthResponse tokenRefreshing(RefreshTokenRequest refreshTokenRequest) {
        if (jwtUtil.validateToken(refreshTokenRequest.getRefreshToken(), TokenType.REFRESH_TOKEN)) {
            User user = userRepository.findByRefreshTokenAndUseFlag(refreshTokenRequest.getRefreshToken(), UseType.USE.getIsUse())
                    .orElseThrow();
            return getAuthResponse(user, jwtUtil, userRepository);
        }
        throw new IllegalArgumentException("유효하지 않은 토큰 정보");
    }

    @Transactional
    public AuthResponse getAuthResponse(User user, JwtUtil jwtUtil, UserRepository userRepository) {
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        String accessToken = jwtUtil.createToken(userPrincipal, TokenType.ACCESS_TOKEN);
        String refreshToken = jwtUtil.createToken(userPrincipal, TokenType.REFRESH_TOKEN);

        user.setRefreshToken(refreshToken);
        userRepository.save(user);
        return new AuthResponse(accessToken, refreshToken);
    }
}
