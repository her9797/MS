package com.ms.back.notice.entity;

import com.ms.back.common.BaseTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Entity
@Getter
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

    @Column(name = "delete_Yn", nullable = false)
    private String deleteYn;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    protected Notice() {}

    // deleteYn 상태를 설정하는 메서드
    public void markAsDeleted() {
        this.deleteYn = "Y";
    }

    // deleteYn 상태를 복구하는 메서드
    public void markAsNotDeleted() {
        this.deleteYn = "N";
    }
}
