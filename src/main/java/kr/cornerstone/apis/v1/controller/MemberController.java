package kr.cornerstone.apis.v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.cornerstone.apis.v1.service.MemberService;
import kr.cornerstone.payload.AuthResponse;
import kr.cornerstone.payload.GoogleLoginRequest;
import kr.cornerstone.payload.ResponseCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "MEMBER", description = "멤버 서비스")
@RequestMapping("/v1/user/member")
@RestController("v1.memberController")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",content = @Content(array = @ArraySchema(schema = @Schema(implementation = AuthResponse.class)))),
    })
    @Operation(summary = "구글 계정 로그인")
    @PostMapping("/google/login")
    public ResponseEntity<AuthResponse> googleLogin(@RequestBody GoogleLoginRequest googleLoginRequest){
        AuthResponse authResponse = memberService.googleLogin(googleLoginRequest);
        return ResponseEntity.ok().body(authResponse);
    }

}
