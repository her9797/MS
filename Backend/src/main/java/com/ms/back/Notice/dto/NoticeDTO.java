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

    private LocalDateTime createdAt;

    private LocalDateTime modifiedDate;

    public NoticeDTO(int noticeNo, String title, String content, char deleteYn, LocalDateTime createdAt) {
        this.noticeNo = noticeNo;
        this.title = title;
        this.content = content;
        this.deleteYn = deleteYn;
        this.createdAt = createdAt;
    }
}
