package com.ms.back.Comment.service;

import com.ms.back.Comment.dto.CmtDTO;
import com.ms.back.Notice.dto.NoticeDTO;
import com.ms.back.Notice.entity.Notice;
import com.ms.back.Notice.service.NoticeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@SpringBootTest
class CommentServiceTest {

    @Autowired
    private CmtService cmtService;

    @Test
    @DisplayName("댓글 등록 테스트")
    void insertCmtTests() {

        // given
        CmtDTO cmtDTO = new CmtDTO(2, "댓글 내용", "userId", "userPws", "N", LocalDateTime.now());

        // when
        Map<String, Object> result = new HashMap<>();

        try {
            cmtService.insertCmt(cmtDTO);
            result.put("result", true);
            System.out.println("cmtDTO : " + cmtDTO);
        } catch (Exception e) {
            cmtService.insertCmt(cmtDTO);
            result.put("result", false);
        }

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.get("result"), true);

    }

}
