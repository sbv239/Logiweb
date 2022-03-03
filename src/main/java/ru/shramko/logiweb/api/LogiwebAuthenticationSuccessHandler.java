package ru.shramko.logiweb.api;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class LogiwebAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        boolean hasManagerRole = false;
        boolean hasDriverRole = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_MANAGER")) {
                hasManagerRole = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_DRIVER")) {
                hasDriverRole = true;
                break;
            }
        }

        if (hasManagerRole) {
            redirectStrategy.sendRedirect(request, response, "/crm/trucks/all");
        } else if (hasDriverRole) {
            redirectStrategy.sendRedirect(request, response, "/profile");
        } else {
            throw new IllegalStateException();
        }
    }
}
