package com.ms.back.Chatting.service;

import com.ms.back.Chatting.dto.JoinedUserDTO;
import com.ms.back.Chatting.entity.JoinedUser;
import com.ms.back.Chatting.entity.Room;
import com.ms.back.Chatting.repository.JoinedUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

    /** 방에 해당하는 유저 등록 */
    public Map<String, Object> insertJoinedUser(JoinedUserDTO joinedUserDTO) {

        Map<String, Object> result = new HashMap<>();

        try {
            JoinedUser userEntity = modelMapper.map(joinedUserDTO, JoinedUser.class);
            joinedUserRepository.save(userEntity);

            result.put("result", true);
        } catch (Exception e) {

            log.error(e.getMessage());
            result.put("result", false);
        }
        return result;

    }
}
