package kr.cornerstone.domain.user;

import kr.cornerstone.domain.auth.AuthService;
import kr.cornerstone.domain.user.payload.GoogleLoginRequest;
import kr.cornerstone.domain.user.payload.GoogleSignUpRequest;
import kr.cornerstone.global.config.jwt.JwtUtil;
import kr.cornerstone.global.enums.UseType;
import kr.cornerstone.global.payload.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final AuthService authService;

    @Override
    @Transactional
    public AuthResponse googleLogin(GoogleLoginRequest googleLoginRequest) {
        User user = userRepository.findByGoogleIdAndUseFlag(googleLoginRequest.getGoogleId(), UseType.USE.getIsUse())
                .orElseThrow(() -> new IllegalArgumentException("일치하는 회원 정보가 없습니다."));
        return authService.getAuthResponse(user, jwtUtil, userRepository);
    }

    @Override
    @Transactional
    public Long googleSignUp(GoogleSignUpRequest googleSignUpRequest) {

        return null;
    }
}
