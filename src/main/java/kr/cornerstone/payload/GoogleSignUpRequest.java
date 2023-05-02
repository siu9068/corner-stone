package kr.cornerstone.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GoogleSignUpRequest {
    @Schema(description = "구글아이디", example = "1234")
    private String googleId;
    @Schema(description = "이름", example = "이광복")
    private String name;
    @Schema(description = "생년월일", example = "19900608")
    private String birth;
}
