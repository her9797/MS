package com.ms.back.Notice.service;

import com.ms.back.Notice.dto.NoticeDTO;
import com.ms.back.Notice.entity.Notice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


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
    }

    @Test
    @DisplayName("공지 등록 테스트")
    void insertNotice() {

        // given
        NoticeDTO notice = new NoticeDTO(2, "제목", "내용", 'N', LocalDateTime.now());

        // when
        Map<String, Object> result = new HashMap<>();

        try {
            noticeService.insertNotice(notice);
            result.put("result", true);
            System.out.println("notice : " + notice);
        } catch (Exception e) {
            noticeService.insertNotice(notice);
            result.put("result", false);
        }

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.get("result"), true);

    }

    @Test
    @DisplayName("공지 수정 테스트")
    void modifyNotice() {

        // given
        int noticeNo = 2;

        NoticeDTO notice = new NoticeDTO();
        notice.setContent("수정한 내용");
        notice.setTitle("수정한 제목");
        notice.setModifiedDate(LocalDateTime.now());

        // when
        Map<String, Object> result = new HashMap<>();
        result.put("result", noticeService.updateNotice(noticeNo, notice));

        // then
        Assertions.assertNotNull(result);
    }

}
