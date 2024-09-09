package com.ms.back.comment.dto;

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

    private String cmtContent;

    private String userEmail;

    private String userPwd;

    private String privateYn;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;


    public CmtDTO( int noticeNo, String cmtContent, String userEmail, String userPwd, String privateYn, LocalDateTime createdDate) {
        this.noticeNo = noticeNo;
        this.cmtContent = cmtContent;
        this.userEmail = userEmail;
        this.userPwd = userPwd;
        this.privateYn = privateYn;
        this.createdDate = createdDate;
    }

    public CmtDTO(int commentNo, int noticeNo, String cmtContent, String userEmail, String userPwd, String privateYn, LocalDateTime createdDate) {
        this.commentNo = commentNo;
        this.noticeNo = noticeNo;
        this.cmtContent = cmtContent;
        this.userEmail = userEmail;
        this.userPwd = userPwd;
        this.privateYn = privateYn;
        this.createdDate = createdDate;
    }

    public CmtDTO(String cmtContent, LocalDateTime modifiedDate) {
        this.cmtContent = cmtContent;
        this.modifiedDate = modifiedDate;
    }
}
