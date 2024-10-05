package com.ms.back.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "user_email")
    private String userEmail;

    @Builder
    public UserProfile(Long id, String fileName, String userEmail) {
        this.id = id;
        this.fileName = fileName;
        this.userEmail = userEmail;
    }

    protected UserProfile() {
    }
}
