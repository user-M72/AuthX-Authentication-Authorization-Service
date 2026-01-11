package AuthX.Authorization_Service.dto;

import AuthX.Authorization_Service.entity.Role;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String email,
        String password,
        Role role,
        boolean enabled,
        LocalDateTime createAt,
        LocalDateTime updateAt
) {
}