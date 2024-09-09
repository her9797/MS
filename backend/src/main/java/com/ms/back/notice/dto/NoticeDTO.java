package com.ms.back.notice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NoticeDTO {

    private int noticeNo;

    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 100, message = "제목은 최대 100자입니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    @Size(max = 1000, message = "내용은 최대 1000자입니다.")
    private String content;

    private char deleteYn;

    private String userEmail;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public NoticeDTO(String title, String content, LocalDateTime modifiedDate, String userEmail) {
        this.title = title;
        this.content = content;
        this.modifiedDate = modifiedDate;
        this.userEmail = userEmail;
    }



    public NoticeDTO(int noticeNo, String title, String content, char deleteYn, LocalDateTime createdDate, String userEmail) {
        this.noticeNo = noticeNo;
        this.title = title;
        this.content = content;
        this.deleteYn = deleteYn;
        this.createdDate = createdDate;
        this.userEmail = userEmail;
    }


}
