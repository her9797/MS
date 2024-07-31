package com.ms.back.Comment.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CmtDTO {

    private int commentNo;

    private int noticeNo;

    private String cmcContent;

    private String userId;

    private String userPwd;

    private String privateYn;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

}
