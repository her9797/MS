package com.ms.back.Comment.repository;

import com.ms.back.Comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CmtRepository extends JpaRepository<Comment, Integer> {

    Comment findByCommentNo(int cmtNo);
}
