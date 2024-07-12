package com.ms.back.Notice.entity;

import com.ms.back.Common.Date.BaseTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Notice extends BaseTime {

    @Id
    @Column(name = "notice_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noticeNo;

    @NotBlank(message = "제목은 필수 입력 사항입니다.")
    @Column(name = "title", nullable = false)
    private String title;

    @NotBlank(message = "내용은 필수 입력 사항입니다.")
    @Column(name = "content", nullable = false)
    private String content;



}
