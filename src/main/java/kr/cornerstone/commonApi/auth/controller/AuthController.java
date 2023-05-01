package kr.cornerstone.commonApi.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.cornerstone.commonApi.auth.service.AuthService;
import kr.cornerstone.payload.AuthResponse;
import kr.cornerstone.payload.RefreshTokenRequest;
import kr.cornerstone.payload.ResponseCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "AUTH", description = "토큰발행 서비스")
@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "401", description = "신뢰할수 없는 토큰 (재로그인 진행바람)"),
    })
    @Operation(summary = "토큰재발급", description = "만료된 토큰을 재발급합니다.")
    @PostMapping("/refresh")
    public ResponseEntity tokenRefreshing(@RequestBody RefreshTokenRequest refreshTokenRequest){
        try {
            AuthResponse authResponse = authService.tokenRefreshing(refreshTokenRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
        } catch (Exception e) {
            return ResponseCustom.ofError(HttpStatus.UNAUTHORIZED,"유효하지 않은 리프레쉬토큰");
        }
    }
}