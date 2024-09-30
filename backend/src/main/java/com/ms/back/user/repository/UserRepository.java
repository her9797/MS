package com.ms.back.user.repository;

import com.ms.back.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUserEmail(String userEmail);

    Optional<User> findUserByUserEmail(String userEmail);
}
