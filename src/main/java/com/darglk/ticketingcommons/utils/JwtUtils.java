package com.darglk.ticketingcommons.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.time.Instant;
import java.util.Date;

public class JwtUtils {
    public static String generateToken(String username, String userId) {

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, Keys.hmacShaKeyFor("kurwajapierdolemozedluzszytenkluczpierdolonykurwajapierdolemozedluzszytenkluczpierdolonykurwajapierdolemozedluzszytenkluczpierdolonykurwajapierdolemozedluzszytenkluczpierdolonykurwajapierdolemozedluzszytenkluczpierdolonykurwajapierdolemozedluzszytenkluczpierdolonykurwajapierdolemozedluzszytenkluczpierdolonykurwajapierdolemozedluzszytenkluczpierdolonykurwajapierdolemozedluzszytenkluczpierdolonykurwajapierdolemozedluzszytenkluczpierdolonykurwajapierdolemozedluzszytenkluczpierdolonykurwajapierdolemozedluzszytenkluczpierdolony".getBytes()))
                .setIssuer("dupa")
                .setAudience("dupa")
                .setSubject(username)
                .setId(userId)
                .setExpiration(new Date(System.currentTimeMillis() + 8640000))
                .setIssuedAt(Date.from(Instant.now()))
                .compact();
    }
}
