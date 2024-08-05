package com.ms.back.Comment.service;

import com.ms.back.Comment.dto.CmtDTO;
import com.ms.back.Comment.entity.Comment;
import com.ms.back.Comment.repository.CmtRepository;
import com.ms.back.Notice.dto.NoticeDTO;
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

    /** 댓글 수정 */
    @Transactional
    public Map<String, Object> modifyCmt(int cmtNo, CmtDTO cmtDTO) {

        Map<String, Object> result = new HashMap<>();

        Comment cmtEntity = cmtRepository.findByCommentNo(cmtNo);

        if (cmtEntity != null) {

            CmtDTO cmt = modelMapper.map(cmtEntity, CmtDTO.class);

            cmt.setCmtContent(cmtDTO.getCmtContent());
            cmt.setModifiedDate(cmtDTO.getModifiedDate());

            Comment updateCmt = modelMapper.map(cmt, Comment.class);
            cmtRepository.save(updateCmt);

            result.put("result", true);
        } else {
            result.put("result", false);
        }
        return result;

    }

    public Map<String, Object> deleteCmt(int cmtNo) {

        Map<String, Object> result = new HashMap<>();
        result.put("result", true);

        Comment comment = cmtRepository.findByCommentNo(cmtNo);

        if (comment != null) {

            cmtRepository.delete(comment);
        }

        return result;

    }
}
