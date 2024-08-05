package com.ms.back.Comment.controller;

import com.ms.back.Comment.dto.CmtDTO;
import com.ms.back.Comment.service.CmtService;
import com.ms.back.Common.ResponseMessage;
import com.ms.back.Notice.dto.NoticeDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class CmtController {

    private final CmtService cmtService;

    @Autowired
    public CmtController(CmtService cmtService) {
        this.cmtService = cmtService;
    }

    /** 댓글 등록 */
    @PostMapping("/comments")
    public ResponseEntity<ResponseMessage> insertNotice(@RequestBody CmtDTO CmtDTO) {

        CmtDTO.setCreatedDate(LocalDateTime.now());

        return ResponseEntity.ok().body(new ResponseMessage(200, "등록 성공", cmtService.insertCmt(CmtDTO)));

    }

    /** 댓글 수정 */
    @PatchMapping("/comments/{commentNo}")
    public ResponseEntity<ResponseMessage> modifyComment(@PathVariable("commentNo") int commentNo,
                                                         @RequestBody CmtDTO cmtDTO) {

        return ResponseEntity.ok().body(new ResponseMessage(200, "수정 성공", cmtService.modifyCmt(commentNo, cmtDTO)));

    }

    /** 댓글 삭제 */
    @DeleteMapping("/comments/{commentNo}")
    public ResponseEntity<ResponseMessage> deleteComment(@PathVariable("commentNo") int commentNo) {

        return ResponseEntity.ok().body(new ResponseMessage(200, "삭제 성공", cmtService.deleteCmt(commentNo)));
    }


}
