package com.ms.back.user.service;

import com.ms.back.common.Jwt.JwtUtils;
import com.ms.back.user.dto.UserRole;
import com.ms.back.user.entity.User;
import com.ms.back.user.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class AuthService {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration-time}")
    private long expirationTime;

    final String GOOGLE_URL = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=";

    final String KAKAO_URL = "https://kapi.kakao.com/v2/user/me";

    private final RestTemplate restTemplate;

    private final UserService userService;

    private final UserRepository userRepository;


    @Autowired
    public AuthService(RestTemplate restTemplate
                       ,UserService userService
                       ,UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.userService = userService;
        this.userRepository = userRepository;
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
        User findUser = userService.findByUserEmail(email);
        if (findUser == null) {
            userService.insertUser(name, email, UserRole.USER, "ACTIVE");
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

    @Transactional
    public String authenticateWithKakao(String token) throws Exception {

        // Kakao API 호출 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Kakao 사용자 정보 조회
        ResponseEntity<Map> responseEntity;
        try {
            responseEntity = restTemplate.exchange(KAKAO_URL, HttpMethod.GET, entity, Map.class);
        } catch (HttpClientErrorException e) {
            throw new Exception("HTTP Error: " + e.getStatusCode() + " " + e.getResponseBodyAsString());
        } catch (Exception e) {
            throw new Exception("Failed to connect to Kakao API: " + e.getMessage());
        }

        // 응답 상태 코드 확인
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new Exception("Invalid token. Response code: " + responseEntity.getStatusCode());
        }
        Map<String, Object> response = responseEntity.getBody();
        if (response == null || !response.containsKey("id")) {
            throw new Exception("Invalid token. Response body: " + response);
        }

        // Kakao 사용자 정보 추출
        Object idObject = response.get("id");
        String kakaoId;
        if (idObject instanceof Long) {
            kakaoId = String.valueOf(idObject);
        } else if (idObject instanceof String) {
            kakaoId = (String) idObject;
        } else {
            throw new ClassCastException("ID is not of type Long or String");
        }

        Map<String, Object> properties = (Map<String, Object>) response.get("properties");

        String nickname = properties != null ? (String) properties.get("nickname") : null;

        // 사용자 정보를 데이터베이스에 저장 또는 업데이트
        User user = userRepository.findByUserEmail(kakaoId);

        if (user == null) {
            userService.insertUser(nickname, kakaoId, UserRole.USER, "ACTIVE");
        }

        // JWT 생성
        String jwtToken = Jwts.builder()
                .setSubject(kakaoId)
                .claim("name", nickname)
                .claim("email", kakaoId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();


        return jwtToken;
    }
}