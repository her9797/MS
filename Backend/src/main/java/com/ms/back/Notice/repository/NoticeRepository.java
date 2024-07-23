package com.ms.back.Notice.repository;


import com.ms.back.Notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoticeRepository extends JpaRepository<Notice, Integer> {
}
