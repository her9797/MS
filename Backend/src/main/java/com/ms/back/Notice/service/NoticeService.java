package com.ms.back.Notice.service;

import com.ms.back.Notice.entity.Notice;
import com.ms.back.Notice.repository.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class NoticeService {

    private final ModelMapper modelMapper;

    private final NoticeRepository noticeRepository;

    @Autowired
    public NoticeService(ModelMapper modelMapper, NoticeRepository noticeRepository) {
        this.modelMapper = modelMapper;
        this.noticeRepository = noticeRepository;
    }

    /** 페이징 + 공지사항 목록 가져오기 */
    public Page<Notice> selectNoticeLists(int page, int size) {

        // PageRequest 객체로 쉽게 페이지 가져올 수 있음
        PageRequest pageRequest = PageRequest.of(page, size);

        return noticeRepository.findAll(pageRequest);
    }
}
