package AuthX.Authorization_Service.service;

import AuthX.Authorization_Service.dto.UserRequestDto;
import AuthX.Authorization_Service.dto.UserResponseDto;
import AuthX.Authorization_Service.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserResponseDto> get();

    UserResponseDto getById(UUID id);

    UserResponseDto create(UserRequestDto request);

    UserResponseDto getByEmail(String email);

    UserResponseDto toDto(User user);
}
