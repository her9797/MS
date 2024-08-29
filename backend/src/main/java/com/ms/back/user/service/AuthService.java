package com.ms.back.user.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;

@Service
public class AuthService {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration-time}")
    private long expirationTime;

    private final RestTemplate restTemplate;

    @Autowired
    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String authenticateWithGoogle(String token) throws Exception {
        String url = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=" + token;
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if (response == null || !response.containsKey("sub")) {
            throw new Exception("Invalid token");
        }

        String userId = (String) response.get("sub");
        String name = (String) response.get("name");
        String email = (String) response.get("email");

        // JWT 생성
        return Jwts.builder()
                .setSubject(userId)
                .claim("name", name)
                .claim("email", email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}