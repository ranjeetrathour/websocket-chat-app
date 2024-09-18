package com.example.Utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;



public class JwtUtils {

    @Value("${app.security.expire}")
    private long jwtExpirationDate;
    @Value("${app.security.expire}")
    private String jwtSecret;
    public String generateToke(UserDetails userDetails){
        Map<String, Objects> claims = new HashMap<>();
        return createToken(userDetails.getUsername(), claims);
    }

    private String createToken(String username, Map<String, Objects> claims) {
        return Jwts.builder()
                .setSubject(username)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ TimeUnit.MILLISECONDS.toSeconds(jwtExpirationDate)))
                .signWith(key())
                .compact();
        }

    private SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUsername(String token){

    }
}

