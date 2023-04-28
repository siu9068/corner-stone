package kr.cornerstone.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UseType {
    USE("Y",true,"사용"),
    NOT_USE("N",false,"미사용");

    private final String code;
    private final Boolean isUse;
    private final String txt;
}
