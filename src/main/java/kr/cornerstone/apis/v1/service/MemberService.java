package kr.cornerstone.apis.v1.service;

import kr.cornerstone.payload.AuthResponse;
import kr.cornerstone.payload.GoogleLoginRequest;
import kr.cornerstone.payload.GoogleSignUpRequest;


public interface MemberService{
    AuthResponse googleLogin(GoogleLoginRequest googleLoginRequest);

    Long googleSignUp(GoogleSignUpRequest googleSignUpRequest);
}
