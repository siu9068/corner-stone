package kr.cornerstone.commonApi.auth.service;

import kr.cornerstone.config.jwt.JwtUtil;
import kr.cornerstone.config.jwt.TokenType;
import kr.cornerstone.config.security.UserPrincipal;
import kr.cornerstone.entity.User;
import kr.cornerstone.enums.UseType;
import kr.cornerstone.payload.AuthResponse;
import kr.cornerstone.payload.RefreshTokenRequest;
import kr.cornerstone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
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
