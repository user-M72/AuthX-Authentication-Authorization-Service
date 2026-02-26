package AuthX.Authorization_Service.service;

import AuthX.Authorization_Service.dto.AuthResponseDto;
import AuthX.Authorization_Service.dto.LoginRequestDto;
import AuthX.Authorization_Service.dto.RegisterRequestDto;
import AuthX.Authorization_Service.dto.UserResponseDto;
import org.springframework.security.core.Authentication;

public interface AuthService {

    AuthResponseDto register(RegisterRequestDto request);

    AuthResponseDto login(LoginRequestDto request);

    AuthResponseDto refresh(String refreshToken);

    void logout(String refreshToken);

    Authentication verifyAccessToken(String accessToken);

    UserResponseDto getCurrentUserData(String accessToken);
}
