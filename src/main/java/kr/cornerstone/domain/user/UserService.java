package kr.cornerstone.domain.user;

import kr.cornerstone.domain.user.payload.GoogleLoginRequest;
import kr.cornerstone.domain.user.payload.GoogleSignUpRequest;
import kr.cornerstone.global.payload.AuthResponse;


public interface UserService {
    AuthResponse googleLogin(GoogleLoginRequest googleLoginRequest);

    Long googleSignUp(GoogleSignUpRequest googleSignUpRequest);
}
