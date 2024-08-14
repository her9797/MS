package com.ms.back.Chatting.repository.JoinedUser;

import com.ms.back.Chatting.entity.JoinedUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JoinedUserRepository extends JpaRepository<JoinedUser, Integer> {

}
