package com.ms.back.comment.service;

import com.ms.back.comment.dto.CmtDTO;
import com.ms.back.comment.entity.Comment;
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
class CommentServiceTest {

    @Autowired
    private CmtService cmtService;

    @Test
    @DisplayName("댓글 조회(페이징) 테스트")
    void selectCmtPageTest() {

        // given
        int page = 1;
        int size = 10;

        // when
        Page<Comment> cmtList = cmtService.selectCmtLists(page, size);

        // then
        Assertions.assertNotNull(cmtList);
    }

    @Test
    @DisplayName("댓글 등록 테스트")
    void insertCmtTest() {

        // given
        CmtDTO cmtDTO = new CmtDTO(2, "댓글 내용", "userEmail", "userPws", "N", LocalDateTime.now());

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

    @Test
    @DisplayName("댓글 수정 테스트")
    void modifyCmtTest() {

        // given
        int cmtNo = 2;

        CmtDTO cmtDTO = new CmtDTO();
        cmtDTO.setCmtContent("수정한 댓글 내용");
        cmtDTO.setModifiedDate(LocalDateTime.now());

        // when
        Map<String, Object> result = new HashMap<>();
        result.put("result", cmtService.modifyCmt(cmtNo, cmtDTO));

        // then
        Assertions.assertNotNull(result);

    }

    @Test
    @DisplayName("댓글 삭제 테스트")
    void deleteCmtTest() {

        // given
        int cmtNo = 1;

        // when
        Map<String, Object> result = cmtService.deleteCmt(cmtNo);

        // then
        Assertions.assertNotNull(result);

    }

}
