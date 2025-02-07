package com.ms.back.comment.entity;

import com.ms.back.common.BaseTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Entity
@Getter
public class Comment extends BaseTime {

    @Id
    @Column(name = "comment_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentNo;

    @Column(name = "notice_no", nullable = false)
    private int noticeNo;

    @Column(name = "cmt_content", nullable = false)
    @NotBlank(message = "내용은 필수 입력 사항입니다.")
    private String cmtContent;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "user_pwd", nullable = false)
    private String userPwd;

    @Column(name = "private_yn", nullable = false)
    private String privateYn;

    protected Comment() {}
}
