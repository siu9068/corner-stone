package kr.cornerstone.domain.user;

import kr.cornerstone.domain.user.payload.AppleSignInRequest;
import kr.cornerstone.domain.user.payload.AppleSignUpRequest;
import kr.cornerstone.domain.user.payload.GoogleSignInRequest;
import kr.cornerstone.domain.user.payload.GoogleSignUpRequest;
import kr.cornerstone.global.payload.AuthResponse;


public interface UserService {
    AuthResponse googleSignIn(GoogleSignInRequest googleSignInRequest);

    Long googleSignUp(GoogleSignUpRequest googleSignUpRequest);

    Long appleSignUp(AppleSignUpRequest appleSignUpRequest);

    AuthResponse appleSignIn(AppleSignInRequest appleSignInRequest);

    void MembershipCancellation(Long userId);
}
