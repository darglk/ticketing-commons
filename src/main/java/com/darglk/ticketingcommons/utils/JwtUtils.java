package com.darglk.ticketingcommons.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.util.Strings;

import java.time.Instant;
import java.util.Date;

public class JwtUtils {
    public static String generateToken(String username, String userId) {

        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(System.getenv("JWT_SECRET").getBytes()), SignatureAlgorithm.HS512)
                .setIssuer(System.getenv("JWT_ISSUER"))
                .setAudience(System.getenv("JWT_AUDIENCE"))
                .setSubject(userId)
                .setId(username)
                .setExpiration(new Date(System.currentTimeMillis()
                        + Long.parseLong(System.getenv("JWT_EXPIRATION_MILLIS"))))
                .setIssuedAt(Date.from(Instant.now()))
                .compact();
    }

    public static Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(System.getenv("JWT_SECRET").getBytes())
                .build()
                .parseClaimsJws(token.replace("Bearer ", Strings.EMPTY));
    }
}
