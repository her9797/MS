package com.ms.back.Common.Date;

import lombok.Getter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseTime {

    /** @MappedSuperclass
       : DB 테이블 반영 X
       : 단독 테이블 사용 X -> abstract(추상 클래스)로 선언
    */

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

}
