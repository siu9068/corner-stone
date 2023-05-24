package kr.cornerstone.global.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErrorResponse {

    @Schema(description = "에러 메세지",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "원인")
    private String message;

    @Schema(description = "발생 시간",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "2023-05-24T16:05:01.058Z")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime timestamp = LocalDateTime.now();

    public ErrorResponse(String message) {
        this.message = message;
    }
}
