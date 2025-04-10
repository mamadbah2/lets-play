package sn.dev.letsplay.services;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

public interface JWTService {
    String generateToken(String username);

    String extractUsername(String token);

    boolean validateToken(String token, UserDetails userDetails);

    <T> T extractClaim(String token, Function<Claims, T> claimResolver);

    Claims extractAllClaims(String token);

    boolean isTokenExpired(String token);

    Date extractExpiration(String token);
}
