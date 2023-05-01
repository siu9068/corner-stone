package kr.cornerstone.commonApi.auth.service;

import kr.cornerstone.config.jwt.JwtUtil;
import kr.cornerstone.entity.User;
import kr.cornerstone.payload.AuthResponse;
import kr.cornerstone.payload.RefreshTokenRequest;
import kr.cornerstone.repository.UserRepository;

public interface AuthService {
    AuthResponse getAuthResponse(User user, JwtUtil jwtUtil, UserRepository userRepository);
    AuthResponse tokenRefreshing(RefreshTokenRequest refreshTokenRequest);
}
