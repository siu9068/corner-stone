package kr.cornerstone.domain.user.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GoogleSignInRequest {

    @Schema(description = "구글 고유 키값",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "1234")
    @NotNull(message = "구글 고유넘버는 필수 값 입니다.")
    private String googleId;
}
