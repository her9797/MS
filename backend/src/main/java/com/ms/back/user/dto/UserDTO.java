package com.ms.back.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {

    private int userNo;

    private String userId;

    private String userPwd;

    private String userName;

    private String userEmail;

    private String userNickname;

    private UserRole userRole;

    private String userGender;

    private String userStatus;

}
