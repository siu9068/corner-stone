package kr.cornerstone.domain.user;

import kr.cornerstone.domain.auth.AuthService;
import kr.cornerstone.domain.user.enums.AuthType;
import kr.cornerstone.domain.user.payload.AppleLoginRequest;
import kr.cornerstone.domain.user.payload.AppleSignUpRequest;
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

    private static final Boolean USE = true;

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
            boolean alreadySignUp = userRepository.existsByGoogleId(googleSignUpRequest.getGoogleId());

            if(alreadySignUp){
                throw new IllegalArgumentException("이미 가입 된 구글 계정 입니다.");
            }

            User user = User.builder()
                    .googleId(googleSignUpRequest.getGoogleId())
                    .name(googleSignUpRequest.getName())
                    .birth(googleSignUpRequest.getBirth())
                    .authType(AuthType.USER)
                    .build();
            userRepository.save(user);
            return user.getId();
    }

    @Override
    @Transactional
    public Long appleSignUp(AppleSignUpRequest appleSignUpRequest) {
        boolean alreadySignUp = userRepository.existsByAppleId(appleSignUpRequest.getAppleId());

        if(alreadySignUp){
            throw new IllegalArgumentException("이미 가입 된 애플 계정 입니다.");
        }

        User user = User.builder()
                .appleId(appleSignUpRequest.getAppleId())
                .name(appleSignUpRequest.getName())
                .birth(appleSignUpRequest.getBirth())
                .authType(AuthType.USER)
                .build();
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public AuthResponse appleLogin(AppleLoginRequest appleLoginRequest) {
        User user = userRepository.findByAppleIdAndUseFlag(appleLoginRequest.getAppleId(), UseType.USE.getIsUse())
                .orElseThrow(() -> new IllegalArgumentException("일치하는 회원 정보가 없습니다."));
        return authService.getAuthResponse(user, jwtUtil, userRepository);
    }
}
