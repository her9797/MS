package com.ms.back.comment.controller;

import com.ms.back.comment.dto.CmtDTO;
import com.ms.back.comment.entity.Comment;
import com.ms.back.comment.service.CmtService;
import com.ms.back.common.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CmtController {

    private final CmtService cmtService;

    @Autowired
    public CmtController(CmtService cmtService) {
        this.cmtService = cmtService;
    }

    /** 댓글 조회 */
    @GetMapping("/comments")
    public ResponseEntity<ResponseMessage> selectNoticeList (@RequestParam(value = "page", defaultValue = "1") int page,
                                                             @RequestParam(value = "size", defaultValue = "10") int size) {


        Page<Comment> cmtLists = cmtService.selectCmtLists(page - 1,size);

        if (cmtLists.isEmpty()) {
            String errorMessage = "조회 데이터가 존재하지 않습니다";
            ResponseMessage responseMessage = new ResponseMessage(HttpStatus.NOT_FOUND.value(), errorMessage, null);
            return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("comment", cmtLists.getContent());
        response.put("currentPage", cmtLists.getNumber());
        response.put("totalItems", cmtLists.getTotalElements());
        response.put("totalPages", cmtLists.getTotalPages());
        System.out.println(response);
        ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공", response);

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);

    }

    /** 댓글 등록 */
    @PostMapping("/comments")
    public ResponseEntity<ResponseMessage> insertComment(@RequestBody CmtDTO CmtDTO) {

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
