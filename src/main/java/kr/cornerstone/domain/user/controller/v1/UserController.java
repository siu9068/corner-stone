package kr.cornerstone.domain.user.controller.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.cornerstone.domain.user.payload.GoogleLoginRequest;
import kr.cornerstone.domain.user.payload.GoogleSignUpRequest;
import kr.cornerstone.domain.user.UserService;
import kr.cornerstone.global.payload.AuthResponse;
import kr.cornerstone.global.payload.ErrorResponse;
import kr.cornerstone.global.payload.ResponseCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "USER", description = "유저 서비스")
@RequestMapping("/api/v1/user")
@RestController("v1.userController")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",content = @Content(array = @ArraySchema(schema = @Schema(implementation = AuthResponse.class)))),
            @ApiResponse(responseCode = "401", description = "로그인 실패",content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "구글 계정 로그인")
    @PostMapping("/google/login")
    public ResponseEntity<?> googleLogin(@RequestBody GoogleLoginRequest googleLoginRequest){
        try {
            AuthResponse authResponse = userService.googleLogin(googleLoginRequest);
            return ResponseCustom.of(HttpStatus.OK,authResponse);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseCustom.ofError(HttpStatus.UNAUTHORIZED,"일치하는 회원 정보가 없습니다.");
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "가입 성공",content = @Content(schema = @Schema(implementation = Long.class))),
    })
    @Operation(summary = "구글 계정 회원가입")
    @PostMapping("/google/signup")
    public ResponseEntity<?> googleSignUp(@RequestBody GoogleSignUpRequest googleSignUpRequest){
        Long id = userService.googleSignUp(googleSignUpRequest);
        return ResponseCustom.of(HttpStatus.CREATED,id);
    }

}
