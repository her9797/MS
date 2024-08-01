package com.ms.back.Comment.service;

import com.ms.back.Comment.dto.CmtDTO;
import com.ms.back.Comment.entity.Comment;
import com.ms.back.Comment.repository.CmtRepository;
import com.ms.back.Notice.entity.Notice;
import com.ms.back.Notice.repository.NoticeRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class CmtService {

    private final ModelMapper modelMapper;

    private final CmtRepository cmtRepository;

    public CmtService(ModelMapper modelMapper, CmtRepository cmtRepository) {
        this.modelMapper = modelMapper;
        this.cmtRepository = cmtRepository;
    }

    /** 공지사항 내 댓글 등록 */
    @Transactional
    public Map<String, Object> insertCmt(CmtDTO cmtDTO) {

        Map<String, Object> result = new HashMap<>();

        try {
            Comment cmtEntity = modelMapper.map(cmtDTO, Comment.class);
            cmtRepository.save(cmtEntity);

            result.put("result", true);
        } catch (Exception e) {

            log.error(e.getMessage());
            result.put("result", false);
        }
        return result;
    }
}
