package kr.cornerstone.global.util;

import kr.cornerstone.global.config.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class SignInUtil {
    public static Long getUserId(Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return Long.parseLong(userPrincipal.getUsername());
    }
}
