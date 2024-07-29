package com.ms.back.Notice.controller;

import com.ms.back.Common.PageController;
import com.ms.back.Common.ResponseMessage;
import com.ms.back.Notice.dto.NoticeDTO;
import com.ms.back.Notice.entity.Notice;
import com.ms.back.Notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class NoticeController {

    private final NoticeService noticeService;

    @Autowired
    public NoticeController (NoticeService noticeService){
        this.noticeService = noticeService;
    }

    @GetMapping("/notices")
    public ResponseEntity<ResponseMessage> selectNoticeList ( @RequestParam(value = "page", defaultValue = "1") int page,
                                                             @RequestParam(value = "size", defaultValue = "10") int size) {


        Page<Notice> noticeListPaging = noticeService.selectNoticeLists(page - 1,size);

        if (noticeListPaging.isEmpty()) {
            String errorMessage = "조회 데이터가 존재하지 않습니다";
            ResponseMessage responseMessage = new ResponseMessage(HttpStatus.NOT_FOUND.value(), errorMessage, null);
            return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("notice", noticeListPaging.getContent());
        response.put("currentPage", noticeListPaging.getNumber());
        response.put("totalItems", noticeListPaging.getTotalElements());
        response.put("totalPages", noticeListPaging.getTotalPages());
        System.out.println(response);
        ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공", response);

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);

    }

    @PostMapping("/notices")
    public ResponseEntity<ResponseMessage> insertNotice(@RequestBody NoticeDTO noticeDTO) {

        noticeDTO.setCreatedAt(LocalDateTime.now());

        return ResponseEntity.ok().body(new ResponseMessage(200, "등록 성공", noticeService.insertNotice(noticeDTO)));

    }

}
