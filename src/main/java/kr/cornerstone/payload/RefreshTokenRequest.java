package kr.cornerstone.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequest {
    @NotNull(message = "리프레쉬 토큰 값은 필수입니다.")
    @Schema(description = "리프레쉬 토큰", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaXNzIjoiS0JMIiwiaWF0IjoxNjgzMDQyNTcwLCJleHAiOjE2ODU2MzQ1NzB9.ztvO2OLK3KtqSc5vcfkOwbAW0oeMPVP2ij1_HALxM00Bo8i6JCm1sQTcW8PT0psllAjo5p-naE5pXcMKswv3yg")
    private String refreshToken;
}
