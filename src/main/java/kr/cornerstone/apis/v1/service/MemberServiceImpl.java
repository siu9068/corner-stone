package kr.cornerstone.apis.v1.service;

import kr.cornerstone.commonApi.auth.service.AuthService;
import kr.cornerstone.config.jwt.JwtUtil;
import kr.cornerstone.entity.User;
import kr.cornerstone.enums.UseType;
import kr.cornerstone.payload.AuthResponse;
import kr.cornerstone.payload.GoogleLoginRequest;
import kr.cornerstone.payload.GoogleSignUpRequest;
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
    private final AuthService authService;

    @Override
    @Transactional
    public AuthResponse googleLogin(GoogleLoginRequest googleLoginRequest) {
        User user = userRepository.findByGoogleIdAndUseFlag(googleLoginRequest.getGoogleId(), UseType.USE.getIsUse())
                .orElseThrow();
        return authService.getAuthResponse(user, jwtUtil, userRepository);
    }

    @Override
    @Transactional
    public Long googleSignUp(GoogleSignUpRequest googleSignUpRequest) {

        return null;
    }
}
