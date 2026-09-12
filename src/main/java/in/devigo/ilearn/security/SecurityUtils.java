package in.devigo.ilearn.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    public CustomUserDetails getCurrentUser(){
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if(authentication == null ||
            !(authentication.getPrincipal()
            instanceof  CustomUserDetails)){
            throw new IllegalStateException(
                    "No authenticated user found"
            );
        }

        return (CustomUserDetails) authentication.getPrincipal();
    }

    public long getCurrentUserId(){
        return getCurrentUser().getUserId();
    }

    public String getCurrentUserEmail(){
        return getCurrentUser().getEmail();
    }

}
