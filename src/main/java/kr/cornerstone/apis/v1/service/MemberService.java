package kr.cornerstone.apis.v1.service;

import kr.cornerstone.payload.AuthResponse;
import kr.cornerstone.payload.GoogleLoginRequest;


public interface MemberService{
    AuthResponse googleLogin(GoogleLoginRequest googleLoginRequest);
}
