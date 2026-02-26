package AuthX.Authorization_Service.service;

import AuthX.Authorization_Service.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private  Key key;

    private final long accessTokenValidity = 1000* 60 * 15;

    @PostConstruct
    public void init() {
        try {
            this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
            System.out.println("✅ JwtService initialized successfully");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public String generateAccessToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenValidity))
                .signWith(key)
                .compact();
    }

    public String extractEmail(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token, User user){
        try {
            extractEmail(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
