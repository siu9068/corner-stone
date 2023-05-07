package kr.cornerstone.domain.auth;

import kr.cornerstone.global.config.jwt.JwtUtil;
import kr.cornerstone.domain.user.User;
import kr.cornerstone.global.payload.AuthResponse;
import kr.cornerstone.domain.auth.payload.RefreshTokenRequest;
import kr.cornerstone.domain.user.UserRepository;

public interface AuthService {
    AuthResponse getAuthResponse(User user, JwtUtil jwtUtil, UserRepository userRepository);
    AuthResponse tokenRefreshing(RefreshTokenRequest refreshTokenRequest);
}
