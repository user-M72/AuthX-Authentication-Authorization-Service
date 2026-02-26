package AuthX.Authorization_Service.config;

import AuthX.Authorization_Service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final AuthService authService;

    @Override
    public Authentication authenticate(Authentication authentication) {
        final String authToken = authentication.getCredentials().toString();
        return authService.verifyAccessToken(authToken);
    }

}
