package com.ms.back.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {

    private String userEmail;

    private String userPwd;

    private String userName;

    private String userNickname;

    private UserRole userRole;

    private String userGender;

    private UserStatus userStatus;

}
