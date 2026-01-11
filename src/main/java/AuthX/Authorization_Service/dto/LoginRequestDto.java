package AuthX.Authorization_Service.dto;

public record LoginRequestDto(
        String email,
        String password
) {
}
