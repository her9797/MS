package com.ms.back.Notice.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NoticeDTO {

    private int noticeNo;

    private String title;

    private String content;

    private char deleteYn;

    private String userId;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public NoticeDTO(String title, String content, LocalDateTime modifiedDate, String userId) {
        this.title = title;
        this.content = content;
        this.modifiedDate = modifiedDate;
        this.userId = userId;
    }



    public NoticeDTO(int noticeNo, String title, String content, char deleteYn, LocalDateTime createdDate, String userId) {
        this.noticeNo = noticeNo;
        this.title = title;
        this.content = content;
        this.deleteYn = deleteYn;
        this.createdDate = createdDate;
        this.userId = userId;
    }


}
