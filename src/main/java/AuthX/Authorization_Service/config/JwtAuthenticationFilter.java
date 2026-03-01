package AuthX.Authorization_Service.config;

import AuthX.Authorization_Service.config.properties.AuthenticationExceptionEntryPoint;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final CustomAuthenticationManager authenticationManager;
    private final AuthenticationExceptionEntryPoint authEntryPoint;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {


        String authHeader = request.getHeader("Authorization");


        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);


        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(token, token);

        try {
            Authentication authResult = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authResult);


        } catch (Exception ex) {

            SecurityContextHolder.clearContext();
            authEntryPoint.commence(request, response,
                    new AuthenticationException(ex.getMessage()) {
                    });
            return;
        }

        filterChain.doFilter(request, response);
    }
}
