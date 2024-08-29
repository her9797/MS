package com.ms.back.user.entity;

import com.ms.back.user.dto.UserRole;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class User {

    @Id
    @Column(name = "user_no")
    private int userNo;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_pwd")
    private String userPwd;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String userEmail;

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
    public User(int userNo, String userId, String userPwd, String userName, String userEmail, String userNickname, UserRole userRole, String userGender, String userStatus) {
        this.userNo = userNo;
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        this.userRole = userRole;
        this.userGender = userGender;
        this.userStatus = userStatus;
    }
}
