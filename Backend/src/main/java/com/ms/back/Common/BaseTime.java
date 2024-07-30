package com.ms.back.Common;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseTime {

    /** @MappedSuperclass
       : DB 테이블 반영 X
       : 단독 테이블 사용 X -> abstract(추상 클래스)로 선언
    */

    @Column(name = "created_date", updatable = false, nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDate;

    @Column(name = "modified_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime modifiedDate;

}
