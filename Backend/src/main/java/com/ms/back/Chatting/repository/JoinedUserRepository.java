package com.ms.back.Chatting.repository;

import com.ms.back.Chatting.entity.JoinedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoinedUserRepository extends JpaRepository<JoinedUser, Integer> {
}
