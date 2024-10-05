package com.ms.back.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_email", referencedColumnName = "user_email", nullable = false)
    private User user; // User와의 관계 추가

    @Builder
    public UserProfile(Long id, String fileName, User user) {
        this.id = id;
        this.fileName = fileName;
        this.user = user;
    }

    protected UserProfile() {
    }

    public String getImageUrl() {
        return "http://localhost:8080/uploads/" + fileName; // 서버의 이미지 URL
    }
}
