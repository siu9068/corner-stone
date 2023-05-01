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
    @Schema(description = "리프레쉬 토큰")
    private String refreshToken;
}
