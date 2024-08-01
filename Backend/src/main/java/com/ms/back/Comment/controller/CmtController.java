package com.ms.back.Comment.controller;

import com.ms.back.Comment.dto.CmtDTO;
import com.ms.back.Comment.service.CmtService;
import com.ms.back.Common.ResponseMessage;
import com.ms.back.Notice.dto.NoticeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
