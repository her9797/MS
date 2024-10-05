package com.ms.back.user.service;

import com.ms.back.user.entity.User;
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
import java.util.Optional;

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



}
