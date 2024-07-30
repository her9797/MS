package com.ms.back.Notice.service;

import com.ms.back.Notice.dto.NoticeDTO;
import com.ms.back.Notice.entity.Notice;
import com.ms.back.Notice.repository.NoticeRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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

    /** 공지사항 등록 */
    @Transactional
    public Map<String, Object> insertNotice(NoticeDTO notice) {

        Map<String, Object> result = new HashMap<>();

        try {
            Notice noticeEntity = modelMapper.map(notice, Notice.class);
            noticeRepository.save(noticeEntity);

            result.put("result", true);
        } catch (Exception e) {

            log.error(e.getMessage());
            result.put("result", false);
        }
        return result;
    }

    /** 공지사항 수정 */
    @Transactional
    public Map<String,Object> updateNotice(int noticeNo, NoticeDTO notice) {

        Map<String, Object> result = new HashMap<>();

        Notice noticeEntity = noticeRepository.findByNoticeNo(noticeNo);

        if (noticeEntity != null) {

            NoticeDTO noticeDTO = modelMapper.map(noticeEntity, NoticeDTO.class);

            noticeDTO.setContent(notice.getContent());
            noticeDTO.setTitle(notice.getTitle());
            noticeDTO.setModifiedDate(notice.getModifiedDate());

            Notice updateNotice = modelMapper.map(noticeDTO, Notice.class);
            noticeRepository.save(updateNotice);

            result.put("result", true);
        } else {
            result.put("result", false);
        }
        return result;
    }

    public Map<String, Object> deleteNotice(int noticeNo) {
        Map<String, Object> result = new HashMap<>();

        // Notice 엔티티를 데이터베이스에서 조회
        Notice notice = noticeRepository.findByNoticeNo(noticeNo);

        if (notice != null) {
            // deleteYn 상태를 'Y'로 설정
            notice.markAsDeleted();

            // 변경된 엔티티를 데이터베이스에 저장
            noticeRepository.save(notice);

            result.put("result", true);
        } else {
            result.put("result", false);
        }
        return result;
    }
}
