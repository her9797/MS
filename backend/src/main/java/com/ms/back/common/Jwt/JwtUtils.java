package com.ms.back.common.Jwt;

import com.ms.back.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtils {

    private final Key secretKey;
    private final long expiration;

    public JwtUtils(
            @Value("${jwt.secret-key}") String base64SecretKey,
            @Value("${jwt.expiration-time}") long expiration) {
        byte[] decodedKey = Base64.getDecoder().decode(base64SecretKey);
        this.secretKey = Keys.hmacShaKeyFor(decodedKey);
        this.expiration = expiration;
    }

    public String generateToken(String userId, String name, String email) {
        return Jwts.builder()
                .setSubject(userId) // 사용자 ID를 Subject로 설정
                .claim("name", name) // 사용자 이름 클레임 추가
                .claim("email", email) // 사용자 이메일 클레임 추가
                .setIssuedAt(new Date()) // 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // 만료 시간
                .signWith(SignatureAlgorithm.HS256, secretKey) // 서명
                .compact(); // JWT 생성
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println("JWT 검증 오류: " + e.getMessage());
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject(); // 이 경우 이메일을 반환
    }

    public boolean isTokenExpired(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration().before(new Date());
    }

    public Key getSecretKey() {
        return secretKey;
    }
}
