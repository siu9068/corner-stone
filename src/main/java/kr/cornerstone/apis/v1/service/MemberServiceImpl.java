package kr.cornerstone.apis.v1.service;

import kr.cornerstone.config.jwt.JwtUtil;
import kr.cornerstone.config.jwt.TokenType;
import kr.cornerstone.config.security.UserPrincipal;
import kr.cornerstone.entity.User;
import kr.cornerstone.enums.UseType;
import kr.cornerstone.payload.AuthResponse;
import kr.cornerstone.payload.GoogleLoginRequest;
import kr.cornerstone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Transactional
    public AuthResponse googleLogin(GoogleLoginRequest googleLoginRequest) {
        User user = userRepository.findByGoogleIdAndUseFlag(googleLoginRequest.getGoogleId(), UseType.USE.getIsUse())
                .orElseThrow();
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        String accessToken = jwtUtil.createToken(userPrincipal, TokenType.ACCESS_TOKEN);
        String refreshToken = jwtUtil.createToken(userPrincipal, TokenType.REFRESH_TOKEN);

        user.setRefreshToken(refreshToken);
        userRepository.save(user);
       return new AuthResponse(accessToken,refreshToken);
    }
}
