package kr.cornerstone.domain.user.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppleSignUpRequest {
    @Schema(description = "애플 아이디",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "5678")
    private String appleId;

    @Schema(description = "이름",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "이광복")
    private String name;

    @Schema(description = "생년월일",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "19900608")
    private String birth;
}
