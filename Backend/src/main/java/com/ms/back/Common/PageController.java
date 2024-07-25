package com.ms.back.Common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageController {

    public static Pageable getPageable(int page, int size) {

        // 페이지 번호, 페이지 크기, 정렬 기준을 모두 포함하는 Pageable을 생성
        return PageRequest.of(page, size);
    }

}
