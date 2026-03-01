package AuthX.Authorization_Service.config;

import AuthX.Authorization_Service.entity.User;
import AuthX.Authorization_Service.repository.UserRepository;
import AuthX.Authorization_Service.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();

        String email = jwtService.extractEmail(token);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("User not found: " + email));

        if (!user.isEnabled()) {
            throw new BadCredentialsException(email + " is blocked");
        }

        if (!jwtService.isTokenValid(token, user)) {
            throw new BadCredentialsException("Token is invalid or expired");
        }

        return new UsernamePasswordAuthenticationToken(
                user,
                token,
                user.getAuthorities()
        );
    }

}
