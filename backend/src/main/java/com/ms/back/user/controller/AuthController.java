package com.ms.back.user.controller;

import com.ms.back.auth.token.JwtResponse;
import com.ms.back.auth.token.TokenRequest;
import com.ms.back.user.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/google")
    public ResponseEntity<?> googleAuth(@RequestBody TokenRequest request) {
        try {
            // TokenRequest 객체에서 token을 추출
            String jwtToken = authService.authenticateWithGoogle(request.getToken());
            log.info("Google Login Success : {}", jwtToken);
            return ResponseEntity.ok(new JwtResponse(jwtToken));
        } catch (Exception e) {
            // JSON 형식으로 오류 반환
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid token");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

}
