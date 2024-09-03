package com.ms.back.user.service;

import com.ms.back.common.Jwt.JwtUtils;
import com.ms.back.user.dto.UserRole;
import com.ms.back.user.entity.User;
import com.ms.back.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final JwtUtils jwtUtils;


    @Autowired
    public UserService(UserRepository userRepository, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    /** ID로 User 찾기 */
    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    /** User 등록 (소셜 로그인 시, DB에 해당 회원이 없다면) */
    public void insertUser(String userId, String name, String email, UserRole userRole,  String userStatus) {


        User newUser = new User(userId, name, email, userRole, userStatus);
        userRepository.save(newUser);

    }

}
