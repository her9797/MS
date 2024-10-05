package com.ms.back.user.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserProfileDTO {

    private MultipartFile file;

    private String title;

    private String userEmail;


}
