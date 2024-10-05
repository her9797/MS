package com.ms.back.user.repository;

import com.ms.back.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUserEmail(String userEmail);

    Optional<User> findUserByUserEmail(String userEmail);

    @Query("SELECT u FROM User u LEFT JOIN FETCH UserProfile p ON u.userEmail = p.user.userEmail WHERE u.userEmail = :userEmail")
    Optional<User> findUserWithProfileByUserEmail(@Param("userEmail") String userEmail);
}
