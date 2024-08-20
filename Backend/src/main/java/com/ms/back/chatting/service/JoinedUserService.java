package com.ms.back.chatting.service;

import com.ms.back.chatting.repository.JoinedUser.JoinedUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JoinedUserService {

    private final JoinedUserRepository joinedUserRepository;

    private final ModelMapper modelMapper;


    @Autowired
    public JoinedUserService(JoinedUserRepository joinedUserRepository, ModelMapper modelMapper) {
        this.joinedUserRepository = joinedUserRepository;
        this.modelMapper = modelMapper;
    }


}
