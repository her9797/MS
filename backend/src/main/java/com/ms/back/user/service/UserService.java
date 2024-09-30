package com.ms.back.user.service;

import com.ms.back.common.Jwt.JwtUtils;
import com.ms.back.user.dto.UserDTO;
import com.ms.back.user.dto.UserRole;
import com.ms.back.user.entity.User;
import com.ms.back.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;


@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final JwtUtils jwtUtils;

    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, JwtUtils jwtUtils, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.encoder = encoder;
    }

    /** ID로 User 찾기 */
    public User findByUserEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail);
    }

    /** User 등록 (소셜 로그인 시, DB에 해당 회원이 없다면)
     *  해당 insertUser 메서드는 소셜 로그인에서 받아온 정보를 바탕으로 DB에 INSERT 해주게 된다.
     * */
    public void insertUser(String name, String email, UserRole userRole,  String userStatus) {

        User newUser = new User(name, email, userRole, userStatus);
        userRepository.save(newUser);
    }

    /** 일반 유저의 회원가입 기능.
     *  위 insertUser 다른 기능.
     * */
    public String signUp(UserDTO userDTO) {

        // 이메일 유효성 정규식 패턴
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern emailPatternCompiled = Pattern.compile(emailPattern);

        // 비밀번호 유효성 정규식 패턴
        String passwordPattern = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#])[a-zA-Z\\d!@#]{6,15}$";
        Pattern passwordPatternCompiled = Pattern.compile(passwordPattern);

        // 이메일 유효성 검사
        if (!emailPatternCompiled.matcher(userDTO.getUserEmail()).matches()) {
            return "유효하지 않은 이메일 형식입니다.";
        }

        // 비밀번호 유효성 검사
        if (!passwordPatternCompiled.matcher(userDTO.getUserPwd()).matches()) {
            return "비밀번호는 6~15자 사이의 영문자, 숫자 및 특수 기호 (!, @, #)만 포함할 수 있습니다.";
        }

        User findUser = userRepository.findByUserEmail(userDTO.getUserEmail());

        if (findUser != null && findUser.getUserEmail().equals(userDTO.getUserEmail())) {
            return "중복된 ID 입니다.";
        }

        User newUser = User.builder()
                .userEmail(userDTO.getUserEmail())
                .userPwd(encoder.encode(userDTO.getUserPwd()))
                .userName(userDTO.getUserName())
                .userNickname(" ")
                .userRole(UserRole.USER)
                .userGender(" ")
                .userStatus("ACTIVE")
                .build();

        userRepository.save(newUser);

        return "회원가입 성공";
    }

    /** 비밀번호 검증 메서드 */
    public boolean validatePassword(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }

    public List<User> findUserList() {

        return userRepository.findAll();
    }

    /** User 상섿조회 */
    public Optional<User> findUserByUserEmail(String userEmail) {

        return userRepository.findUserByUserEmail(userEmail);

    }
}
