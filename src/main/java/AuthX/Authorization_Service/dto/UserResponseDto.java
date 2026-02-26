package AuthX.Authorization_Service.dto;

import AuthX.Authorization_Service.entity.Role;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String username,
        String email
) {
}