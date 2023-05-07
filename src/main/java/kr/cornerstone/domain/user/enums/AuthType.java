package kr.cornerstone.domain.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthType {
    ADMIN("관리자"),
    USER("사용자");
    private final String txt;
}
