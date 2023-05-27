package kr.cornerstone.domain.user.controller.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.cornerstone.domain.user.payload.AppleSignInRequest;
import kr.cornerstone.domain.user.payload.AppleSignUpRequest;
import kr.cornerstone.domain.user.payload.GoogleSignInRequest;
import kr.cornerstone.domain.user.payload.GoogleSignUpRequest;
import kr.cornerstone.domain.user.UserService;
import kr.cornerstone.global.payload.AuthResponse;
import kr.cornerstone.global.payload.ErrorResponse;
import kr.cornerstone.global.payload.ResponseEntityCustom;
import kr.cornerstone.global.util.SignInUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.headers.HeadersSecurityMarker;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "USER", description = "유저 서비스")
@RequestMapping("/api/v1/user")
@RestController("v1.userController")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = AuthResponse.class)))),
            @ApiResponse(responseCode = "401", description = "로그인 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "구글 계정 로그인")
    @PostMapping("/google/sign-in")
    public ResponseEntity<?> googleLogin(@RequestBody GoogleSignInRequest googleSignInRequest){
        try {
            AuthResponse authResponse = userService.googleSignIn(googleSignInRequest);
            return ResponseEntityCustom.of(HttpStatus.OK,authResponse);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntityCustom.ofError(HttpStatus.UNAUTHORIZED,e.getMessage());
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "가입 성공",content = @Content(schema = @Schema(implementation = Long.class))),
            @ApiResponse(responseCode = "400", description = "입력 값 확인 요망", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @Operation(summary = "구글 계정 회원가입")
    @PostMapping("/google/sign-up")
    public ResponseEntity<?> googleSignUp(@RequestBody GoogleSignUpRequest googleSignUpRequest){
        try {
            Long id = userService.googleSignUp(googleSignUpRequest);
            return ResponseEntityCustom.of(HttpStatus.CREATED,id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntityCustom.ofError(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = AuthResponse.class)))),
            @ApiResponse(responseCode = "401", description = "로그인 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Operation(summary = "애플 계정 로그인")
    @PostMapping("/apple/sign-in")
    public ResponseEntity<?> appleSignIn(@RequestBody AppleSignInRequest appleSignInRequest){
        try {
            AuthResponse authResponse = userService.appleSignIn(appleSignInRequest);
            return ResponseEntityCustom.of(HttpStatus.OK,authResponse);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntityCustom.ofError(HttpStatus.UNAUTHORIZED,e.getMessage());
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "가입 성공",content = @Content(schema = @Schema(implementation = Long.class, description = "가입 고유 키" ,requiredMode = Schema.RequiredMode.REQUIRED))),
            @ApiResponse(responseCode = "400", description = "입력 값 확인 요망", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @Operation(summary = "애플 계정 회원가입")
    @PostMapping("/apple/sign-up")
    public ResponseEntity<?> appleSignUp(@RequestBody AppleSignUpRequest appleSignUpRequest){
        try {
            Long id = userService.appleSignUp(appleSignUpRequest);
            return ResponseEntityCustom.of(HttpStatus.CREATED,id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntityCustom.ofError(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "삭제 성공"),
            @ApiResponse(responseCode = "400", description = "입력 값 확인 요망", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @Operation(summary = "회원 탈퇴", security = {@SecurityRequirement(name = "bearer-key")})
    @DeleteMapping("")
    public ResponseEntity<?> MembershipCancellation(Authentication authentication){
        try {
            userService.MembershipCancellation(SignInUtil.getUserId(authentication));
            return ResponseEntityCustom.of(HttpStatus.NO_CONTENT,null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntityCustom.ofError(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

}
