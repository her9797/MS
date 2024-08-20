package com.ms.back.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageController {

    public static Pageable getPageable(int page, int size) {

        // 페이지 번호, 페이지 크기, 정렬 기준을 모두 포함하는 Pageable을 생성
        // 현재 PageRequest 사용으로 미사용 중
        return PageRequest.of(page, size);
    }

}
