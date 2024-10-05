package com.ms.back.user.controller;

import com.ms.back.user.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
public class ProfileController {

    private final ProfileService userProfileService;

    @Autowired
    public ProfileController(ProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping("/profiles")
    public ResponseEntity<String> uploadUserProfile(@RequestParam("userProfile") MultipartFile file,
                                                    @RequestParam("userEmail") String userEmail) {
        try {
            String fileName = userProfileService.uploadUserProfile(file, userEmail);
            return ResponseEntity.ok("File uploaded successfully: " + fileName);
        } catch (Exception e) {
            log.error("File upload failed", e); // 전체 예외 로그 출력
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }

}
