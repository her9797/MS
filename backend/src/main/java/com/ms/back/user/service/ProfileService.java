package com.ms.back.user.service;

import com.ms.back.user.entity.UserProfile;
import com.ms.back.user.repository.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Value("${file.path}")
    private String uploadFolder;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public String uploadUserProfile(MultipartFile file, String userEmail) throws IOException {
        // 파일 형식 확인
        if (file.isEmpty() || !file.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("Only image files are allowed.");
        }

        // 업로드 디렉토리 설정
        Path path = Paths.get(uploadFolder);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        // 원래 파일 이름과 확장자 추출
        String originalFileName = file.getOriginalFilename();
        String fileName = originalFileName;
        String nameWithoutExt = originalFileName.substring(0, originalFileName.lastIndexOf('.'));
        String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));

        // 중복된 파일 이름 처리
        int count = 1;
        while (Files.exists(path.resolve(fileName))) {
            fileName = nameWithoutExt + "_" + count + extension; // 파일 이름에 숫자 추가
            count++;
        }

        // 파일 저장
        Path filePath = path.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        // UserProfile 객체 생성 및 DB 저장
        UserProfile userProfile = UserProfile.builder()
                .fileName(fileName) // 저장한 파일 이름
                .userEmail(userEmail)
                .build();
        profileRepository.save(userProfile);

        return fileName; // 파일 이름 반환
    }


}
