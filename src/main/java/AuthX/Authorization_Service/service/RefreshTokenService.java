package AuthX.Authorization_Service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final Map<String, UUID> tokenStore = new HashMap<>();

    private final long refreshTokenValidity = 1000 * 60 * 60 * 24 * 7;

    public String create(UUID userId){
        String token =  UUID.randomUUID().toString();
        tokenStore.put(token, userId);

        return token;
    }

    public UUID verify(String token){
        UUID userId = tokenStore.get(token);

        if (userId == null){
            throw new RuntimeException("Invalid refresh token");
        }

        return userId;
    }


    public void revoke(String token){
        tokenStore.remove(token);
    }
}
