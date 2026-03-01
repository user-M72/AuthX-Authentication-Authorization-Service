package AuthX.Authorization_Service.api;

import AuthX.Authorization_Service.dto.AuthResponseDto;
import AuthX.Authorization_Service.dto.LoginRequestDto;
import AuthX.Authorization_Service.dto.RegisterRequestDto;
import AuthX.Authorization_Service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/v1")
public class AuthApi {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto request) {
        AuthResponseDto response = authService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto request) {
        AuthResponseDto response = authService.login(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDto> refresh(@RequestParam("refreshToken") String refreshToken) {
        AuthResponseDto response = authService.refresh(refreshToken);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@RequestParam("refreshToken") String refreshToken) {

        authService.logout(refreshToken);
    }
}