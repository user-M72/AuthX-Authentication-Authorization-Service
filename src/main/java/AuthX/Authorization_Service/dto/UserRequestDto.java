package AuthX.Authorization_Service.dto;


import AuthX.Authorization_Service.entity.Role;

import java.time.LocalDateTime;

public record UserRequestDto(
        String username,
        String email,
        String password,
        Role role
) {
}
