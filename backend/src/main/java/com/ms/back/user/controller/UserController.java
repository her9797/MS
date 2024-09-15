package com.ms.back.user.controller;

import com.ms.back.auth.token.TokenRequest;
import com.ms.back.common.Jwt.JwtUtils;
import com.ms.back.user.dto.UserDTO;
import com.ms.back.user.entity.User;
import com.ms.back.user.repository.UserRepository;
import com.ms.back.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    private final JwtUtils jwtUtils;

    @Autowired
    public UserController(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }



    @PostMapping("/users")
    public String signUp(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO);
        return userService.signUp(userDTO);
    }

    @PostMapping("/users/normal")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        User foundUser = userService.findByUserEmail(user.getUserEmail());
        if (foundUser != null && userService.validatePassword(user.getUserPwd(), foundUser.getUserPwd())) {
            String token = jwtUtils.generateToken(foundUser.getUserEmail());

            TokenRequest tokenRequest = new TokenRequest();
            tokenRequest.setToken(token);

            return ResponseEntity.ok().body(tokenRequest);
        } else {
            return ResponseEntity.status(401).body("Invalid email or password!");
        }
    }

}
