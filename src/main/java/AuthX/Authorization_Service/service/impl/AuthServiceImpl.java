package AuthX.Authorization_Service.service.impl;

import AuthX.Authorization_Service.dto.AuthResponseDto;
import AuthX.Authorization_Service.dto.LoginRequestDto;
import AuthX.Authorization_Service.dto.RegisterRequestDto;
import AuthX.Authorization_Service.service.AuthService;

public class AuthServiceImpl implements AuthService {
    @Override
    public AuthResponseDto register(RegisterRequestDto request) {
        return null;
    }

    @Override
    public AuthResponseDto login(LoginRequestDto request) {
        return null;
    }

    @Override
    public AuthResponseDto refresh(String refreshToken) {
        return null;
    }

    @Override
    public void logout(String refreshToken) {

    }
//    ##TODO;Реализация:
//
//проверка email
//
//хеширование пароля
//
//генерация JWT
//
//работа с refresh token
//
//обращение к UserService
}
