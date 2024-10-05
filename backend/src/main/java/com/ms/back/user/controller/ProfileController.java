package com.ms.back.user.controller;

import com.ms.back.common.ResponseMessage;
import com.ms.back.user.entity.User;
import com.ms.back.user.entity.UserProfile;
import com.ms.back.user.service.ProfileService;
import com.nimbusds.jose.util.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@Slf4j
public class ProfileController {

    private final ProfileService userProfileService;

    @Autowired
    public ProfileController(ProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @Value("${file.path}")
    private String filePath;

//    @PostMapping("/profiles")
//    public ResponseEntity<String> uploadUserProfile(@RequestParam("userProfile") MultipartFile file,
//                                                    @RequestParam("userEmail") String userEmail) {
//        try {
//            String fileName = userProfileService.uploadUserProfile(file, userEmail);
//            return ResponseEntity.ok("파일 등록 성공: " + fileName);
//        } catch (Exception e) {
//            log.error("File upload failed", e); // 전체 예외 로그 출력
//            return ResponseEntity.status(500).body("파일 등록 실패 " + e.getMessage());
//        }
//    }

    @GetMapping("/uploads/{filename:.+}")
    @ResponseBody
    public ResponseEntity<UrlResource> serveFile(@PathVariable("filename") String filename) {
        try {
            // 파일 경로 설정
            Path file = Paths.get(filePath).resolve(filename).normalize();
            UrlResource resource = new UrlResource(file.toUri());

            // 파일 존재 여부 확인
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) // 이미지 유형에 맞게 설정 (필요에 따라 조정)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
