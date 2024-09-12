package com.ms.back.user.entity;

import com.ms.back.user.dto.UserRole;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class User {

    @Id
    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_pwd")
    private String userPwd;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "user_role")
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @Column(name = "user_gender")
    private String userGender;

    @Column(name = "user_satatus")
    private String userStatus;

    protected User(){}

    @Builder
    public User(String userName, String userEmail, UserRole userRole, String userStatus) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }

    /** 얘는 커스텀 빌더임 직접 작성 */
    private User(UserBuilder builder) {
        this.userEmail = builder.userEmail;
        this.userPwd = builder.userPwd;
        this.userName = builder.userName;
        this.userNickname = builder.userNickname;
        this.userRole = builder.userRole;
        this.userGender = builder.userGender;
        this.userStatus = builder.userStatus;
    }

    public static class UserBuilder {
        private String userEmail;
        private String userPwd;
        private String userName;
        private String userNickname;
        private UserRole userRole;
        private String userGender;
        private String userStatus;

        public UserBuilder userEmail(String userEmail) {
            this.userEmail = userEmail;
            return this;
        }

        public UserBuilder userPwd(String userPwd) {
            this.userPwd = userPwd;
            return this;
        }

        public UserBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserBuilder userNickname(String userNickname) {
            this.userNickname = userNickname;
            return this;
        }

        public UserBuilder userRole(UserRole userRole) {
            this.userRole = userRole;
            return this;
        }

        public UserBuilder userGender(String userGender) {
            this.userGender = userGender;
            return this;
        }

        public UserBuilder userStatus(String userStatus) {
            this.userStatus = userStatus;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
