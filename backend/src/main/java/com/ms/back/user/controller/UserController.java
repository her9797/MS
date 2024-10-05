package com.ms.back.user.controller;

import com.ms.back.auth.token.TokenRequest;
import com.ms.back.common.Jwt.JwtUtils;
import com.ms.back.common.ResponseMessage;
import com.ms.back.notice.entity.Notice;
import com.ms.back.user.dto.UserDTO;
import com.ms.back.user.entity.User;
import com.ms.back.user.repository.UserRepository;
import com.ms.back.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final JwtUtils jwtUtils;

    @Autowired
    public UserController(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("")
    public String signUp(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO);
        return userService.signUp(userDTO);
    }

    @PostMapping("/normal")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        User foundUser = userService.findByUserEmail(user.getUserEmail());

        if (foundUser == null) {
            return ResponseEntity.status(401).body("Invalid email or password!");
        }

        // 사용자 상태 체크
        if ("INACTIVE".equals(foundUser.getUserStatus())) {
            return ResponseEntity.status(403).body("Your account is inactive. Please contact support."); // 비활성 계정에 대한 응답
        }

        // 비밀번호 확인
        if (!userService.validatePassword(user.getUserPwd(), foundUser.getUserPwd())) {
            return ResponseEntity.status(401).body("Invalid email or password!");
        }

        // JWT 생성 시 이메일과 이름을 전달
        String token = jwtUtils.generateToken(
                foundUser.getUserEmail(),
                foundUser.getUserName(),
                foundUser.getUserEmail()
        );

        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.setToken(token);

        return ResponseEntity.ok().body(tokenRequest);
    }


    @GetMapping("")
    public ResponseEntity<ResponseMessage> findUserList() {

        List<User> user = userService.findUserList();

        Map<String, Object> results = new HashMap<>();
        results.put("userList", user);

        return ResponseEntity.ok().body(new ResponseMessage(200, "조회 성공", results));
    }

    @GetMapping("/{userEmail}")
    public ResponseEntity<ResponseMessage> findUserDetail(@PathVariable("userEmail") String userEmail) {
        Optional<User> userOptional = userService.findUserWithProfileByEmail(userEmail);

        Map<String, Object> result = new HashMap<>();

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            result.put("user", user);
            ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공", result);
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            ResponseMessage responseMessage = new ResponseMessage(404, "조회 데이터가 없습니다.", null);
            return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("{userEmail}")
    public ResponseEntity<ResponseMessage> modifyUser(@PathVariable ("userEmail") String userEmail, @RequestBody UserDTO userDTO) {

        return ResponseEntity.ok().body(new ResponseMessage(200, "수정 성공", userService.modifyUser(userEmail, userDTO)));
    }

}
