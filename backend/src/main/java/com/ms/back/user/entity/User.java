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

    @Builder
    public User(String userEmail, String userName, String userNickname) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userNickname = userNickname;
    }
}
