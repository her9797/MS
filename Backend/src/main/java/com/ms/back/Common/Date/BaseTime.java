package com.ms.back.Common.Date;

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

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;





}
