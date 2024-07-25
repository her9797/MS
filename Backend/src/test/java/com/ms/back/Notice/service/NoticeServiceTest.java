package com.ms.back.Notice.service;

import com.ms.back.Notice.entity.Notice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;


@SpringBootTest
class NoticeServiceTest {

    @Autowired
    private NoticeService noticeService;

    @Test
    @DisplayName("공지 조회(페이징) 테스트")
    void selectNoticeListTest() {

        // given
        int page = 1;
        int size = 10;

        // when
        Page<Notice> noticeList = noticeService.selectNoticeLists(page, size);

        // then
        Assertions.assertNotNull(noticeList);
        noticeList.forEach(notice -> System.out.println("noticeList : " + notice));
    }

}
