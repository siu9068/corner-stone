package kr.cornerstone.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GoogleLoginRequest {
    @NotBlank
    @Schema(description = "구글 아이디", example = "1234")
    private String googleId;
}
