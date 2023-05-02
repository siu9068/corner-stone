package kr.cornerstone.payload;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
public class AuthResponse {

    @Schema(description = "액세스토큰", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiYXV0aCI6W3siYXV0aG9yaXR5IjoiVVNFUiJ9XSwiaXNzIjoiS0JMIiwiaWF0IjoxNjgzMDQyNTcwLCJleHAiOjE2ODMxMjg5NzB9.NfCR9YJsvVedFPHi2XHNr6ioia_yb75PoWaX100eBYPkxAA4uOFY89TX48s8_u73V64eCZ2o2sVA_tbtAuvuqw")
    private String accessToken;
    @Schema(description = "리프레쉬토큰", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaXNzIjoiS0JMIiwiaWF0IjoxNjgzMDQyNTcwLCJleHAiOjE2ODU2MzQ1NzB9.ztvO2OLK3KtqSc5vcfkOwbAW0oeMPVP2ij1_HALxM00Bo8i6JCm1sQTcW8PT0psllAjo5p-naE5pXcMKswv3yg")
    private String refreshToken;
    @Schema(description = "토큰타입", example = "Bearer")
    private final String tokenType = "Bearer";

    public AuthResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

}
