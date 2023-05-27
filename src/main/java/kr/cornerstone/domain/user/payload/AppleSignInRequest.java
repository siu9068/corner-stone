package kr.cornerstone.domain.user.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppleSignInRequest {

    @Schema(description = "애플 고유 키값",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "5678")
    @NotNull(message = "애플 고유넘버는 필수 값 입니다.")
    private String appleId;
}
