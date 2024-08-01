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


    public CmtDTO( int noticeNo, String cmcContent, String userId, String userPwd, String privateYn, LocalDateTime createdDate) {
        this.noticeNo = noticeNo;
        this.cmcContent = cmcContent;
        this.userId = userId;
        this.userPwd = userPwd;
        this.privateYn = privateYn;
        this.createdDate = createdDate;
    }

    public CmtDTO(int commentNo, int noticeNo, String cmcContent, String userId, String userPwd, String privateYn, LocalDateTime createdDate) {
        this.commentNo = commentNo;
        this.noticeNo = noticeNo;
        this.cmcContent = cmcContent;
        this.userId = userId;
        this.userPwd = userPwd;
        this.privateYn = privateYn;
        this.createdDate = createdDate;
    }
}
