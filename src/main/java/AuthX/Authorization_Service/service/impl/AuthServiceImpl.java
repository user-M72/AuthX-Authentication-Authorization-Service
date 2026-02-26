package AuthX.Authorization_Service.service.impl;

import AuthX.Authorization_Service.dto.AuthResponseDto;
import AuthX.Authorization_Service.dto.LoginRequestDto;
import AuthX.Authorization_Service.dto.RegisterRequestDto;
import AuthX.Authorization_Service.dto.UserResponseDto;
import AuthX.Authorization_Service.entity.User;
import AuthX.Authorization_Service.mapper.AuthMapper;
import AuthX.Authorization_Service.service.AuthService;
import AuthX.Authorization_Service.service.JwtService;
import AuthX.Authorization_Service.service.RefreshTokenService;
import AuthX.Authorization_Service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService tokenService;
    private final AuthMapper mapper;

    @Override
    public AuthResponseDto register(RegisterRequestDto request) {

        var user = mapper.toEntity(request);

        var savedUser = userService.create(user);

        String accessToken = jwtService.generateAccessToken(savedUser.email());
        String refreshToken = tokenService.create(savedUser.id());

        return new AuthResponseDto(accessToken, refreshToken);
    }

    @Override
    public AuthResponseDto login(LoginRequestDto request) {
        UserResponseDto user = userService.getByEmail(request.email());

        if (!passwordEncoder.matches(request.password(), user.password())){
            throw new RuntimeException("Invalid email or password");
        }

        String accessToken = jwtService.generateAccessToken(user.email());
        String refreshToken = tokenService.create(user.id());

        return new AuthResponseDto(accessToken, refreshToken);
    }

    @Override
    public AuthResponseDto refresh(String refreshToken) {
        UUID userId = tokenService.verify(refreshToken);

        UserResponseDto user = userService.getById(userId);

        String newAccessToken = jwtService.generateAccessToken(user.email());

        return new AuthResponseDto(newAccessToken, refreshToken);
    }

    @Override
    public void logout(String refreshToken) {
        tokenService.revoke(refreshToken);
    }
//    ##TODO;Реализация:
//
//генерация JWT
//
//работа с refresh token
//
//обращение к UserService
}
