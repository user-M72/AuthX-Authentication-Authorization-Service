package AuthX.Authorization_Service.dto;

public record AuthResponseDto(

        String accessToken,
        String refreshToken

) {
}
