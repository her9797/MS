package com.ms.back.user.service;

import com.ms.back.user.dto.UserRole;
import com.ms.back.user.entity.User;
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

    final String GOOGLE_URL = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=";

    private final RestTemplate restTemplate;

    private final UserService userService;

    @Autowired
    public AuthService(RestTemplate restTemplate
                       ,UserService userService) {
        this.restTemplate = restTemplate;
        this.userService = userService;
    }

    public String authenticateWithGoogle(String token) throws Exception {
        String url = GOOGLE_URL + token;
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if (response == null || !response.containsKey("sub")) {
            throw new Exception("Invalid token");
        }

        String userId = (String) response.get("sub");
        String name = (String) response.get("name");
        String email = (String) response.get("email");

        // DB에 해당 유저 없을 때, 타는 로직
        User findUser = userService.findByUserId(email);
        if (findUser == null) {
            userService.insertUser(email, name, email, UserRole.USER, "ACTIVE");
        }


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