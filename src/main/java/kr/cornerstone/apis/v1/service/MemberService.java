package kr.cornerstone.apis.v1.service;

import kr.cornerstone.payload.AuthResponse;
import kr.cornerstone.payload.GoogleLoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface MemberService{
    AuthResponse googleLogin(GoogleLoginRequest googleLoginRequest);
}
