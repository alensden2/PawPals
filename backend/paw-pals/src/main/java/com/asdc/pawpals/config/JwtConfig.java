/**

Configuration class for JSON Web Token (JWT) authentication.
*/
package com.asdc.pawpals.config;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Configuration
public class JwtConfig {
    /**
 * The secret key for the JWT.
 */
public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
private static final int TOKEN_EXPIRATION_TIME_MINUTES = 30;
private static final long TOKEN_EXPIRATION_TIME_MS = TOKEN_EXPIRATION_TIME_MINUTES * 60 * 1000;

/**
 * Extracts the username from a given JWT token.
 * @param token the JWT token
 * @return the username
 */
public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
}

/**
 * Extracts the expiration date from a given JWT token.
 * @param token the JWT token
 * @return the expiration date
 */
public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
}

/**
 * Extracts a claim from a given JWT token using a provided Claims resolver.
 * @param token the JWT token
 * @param claimsResolver the Claims resolver
 * @param <T> the type of the Claims resolver
 * @return the claim
 */
public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
}

private Claims extractAllClaims(String token) {
    return Jwts
            .parserBuilder()
            .setSigningKey(getSignKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
}

/**
 * Checks if a given JWT token is expired.
 * @param token the JWT token
 * @return true if expired, false otherwise
 */
private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
}

/**
 * Validates a given JWT token for a given user.
 * @param token the JWT token
 * @param userDetails the user details
 * @return true if the token is valid, false otherwise
 */
public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
}

/**
 * Generates a JWT token for a given user.
 * @param userName the username of the user
 * @return the JWT token
 */
public String generateToken(String userName) {
    Map<String, Object> claims = new HashMap<>();
    return createToken(claims, userName);
}

private String createToken(Map<String, Object> claims, String userName) {
    return Jwts.builder()
            .setClaims(claims)
            .setSubject(userName)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME_MS))
            .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
}

private Key getSignKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET);
    return Keys.hmacShaKeyFor(keyBytes);
}
}