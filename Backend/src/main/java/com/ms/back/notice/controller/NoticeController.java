package com.ms.back.notice.controller;

import com.ms.back.common.ResponseMessage;
import com.ms.back.notice.dto.NoticeDTO;
import com.ms.back.notice.entity.Notice;
import com.ms.back.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class NoticeController {

    private final NoticeService noticeService;

    @Autowired
    public NoticeController (NoticeService noticeService){
        this.noticeService = noticeService;
    }

    /** 공지사항 조회 */
    @GetMapping("/notices")
    public ResponseEntity<ResponseMessage> selectNoticeList (@RequestParam(value = "page", defaultValue = "1") int page,
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

        ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공", response);

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);

    }

    @GetMapping("/notices/{noticeNo}")
    public ResponseEntity<ResponseMessage> selectByNoticeNo(@PathVariable("noticeNo") int noticeNo) {
        // noticeService에서 Optional<Notice>를 가져옴
        Optional<Notice> noticeOptional = noticeService.selectByNoticeId(noticeNo);

        // 응답을 담을 맵
        Map<String, Object> result = new HashMap<>();

        if (noticeOptional.isPresent()) {
            // Optional에서 값을 꺼내서 맵에 넣기
            Notice notice = noticeOptional.get();
            result.put("notice", notice);
            // 조회 성공 메시지
            ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공", result);
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else {
            // 조회 실패 메시지
            ResponseMessage responseMessage = new ResponseMessage(404, "조회 데이터가 없습니다.", null);
            return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
        }
    }

    /** 공지사항 등록 */
    @PostMapping("/notices")
    public ResponseEntity<ResponseMessage> insertNotice(@RequestBody NoticeDTO noticeDTO) {

        noticeDTO.setCreatedDate(LocalDateTime.now());

        return ResponseEntity.ok().body(new ResponseMessage(200, "등록 성공", noticeService.insertNotice(noticeDTO)));

    }

    /** 공지사항 수정 */
    @PatchMapping("/notices/{noticeNo}")
    public ResponseEntity<ResponseMessage> modifyNotice(@PathVariable("noticeNo") int noticeNo,
                                                        @RequestBody NoticeDTO noticeDTO){

        return ResponseEntity.ok().body(new ResponseMessage(200, "수정 성공", noticeService.modifyNotice(noticeNo, noticeDTO)));

    }

    /** 공지사항 삭제 */
    @PutMapping("/notices/{noticeNo}")
    public ResponseEntity<ResponseMessage> deleteNotice(@PathVariable("noticeNo") int noticeNo) {

        return ResponseEntity.ok().body(new ResponseMessage(200, "삭제 성공", noticeService.deleteNotice(noticeNo)));
    }


}
