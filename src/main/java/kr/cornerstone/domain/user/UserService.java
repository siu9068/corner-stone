package kr.cornerstone.domain.user;

import kr.cornerstone.domain.user.payload.AppleLoginRequest;
import kr.cornerstone.domain.user.payload.AppleSignUpRequest;
import kr.cornerstone.domain.user.payload.GoogleLoginRequest;
import kr.cornerstone.domain.user.payload.GoogleSignUpRequest;
import kr.cornerstone.global.payload.AuthResponse;


public interface UserService {
    AuthResponse googleLogin(GoogleLoginRequest googleLoginRequest);

    Long googleSignUp(GoogleSignUpRequest googleSignUpRequest);

    Long appleSignUp(AppleSignUpRequest appleSignUpRequest);

    AuthResponse appleLogin(AppleLoginRequest appleLoginRequest);
}
