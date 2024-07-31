package com.ms.back.Comment.service;

import com.ms.back.Notice.dto.NoticeDTO;
import com.ms.back.Notice.entity.Notice;
import com.ms.back.Notice.service.NoticeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@SpringBootTest
class CommentServiceTest {

    @Autowired
    private NoticeService noticeService;

}
