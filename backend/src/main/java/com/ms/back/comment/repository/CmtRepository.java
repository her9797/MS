package com.ms.back.comment.repository;

import com.ms.back.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CmtRepository extends JpaRepository<Comment, Integer> {

    Comment findByCommentNo(int cmtNo);
}
