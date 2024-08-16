package com.ms.back.Chatting.repository;

import com.ms.back.Chatting.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MsgRepository extends JpaRepository<Message, Integer> {



}
