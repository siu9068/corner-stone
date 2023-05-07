package kr.cornerstone.global.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class StringUtil {
    public static boolean isNullOrBlank(String value) {
        if(value == null || "".equals(value)) {
            return true;
        }
        return false;
    }

    public static Long stringToLong(String value) {
        if(isNullOrBlank(value)) {
            return null;
        }
        return Long.parseLong(value);
    }

    public static LocalDateTime stringToLocalDateTime(String value) {
        if(isNullOrBlank(value)) {
            return null;
        }
        return LocalDateTime.of(LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE), LocalTime.MIN);
    }
}
