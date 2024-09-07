package com.ms.back.chatting.service;

import com.ms.back.chatting.dto.JoinedUserDTO;
import com.ms.back.chatting.entity.JoinedUser;
import com.ms.back.chatting.repository.JoinedUser.JoinedUserRepository;
import com.ms.back.notice.entity.Notice;
import jakarta.transaction.Transactional;
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


    /** 유저 방 나가기 */
    @Transactional
    public Map<String, Object> modifyJoinedStatus(int roomId, JoinedUserDTO joinedUserDTO) {
        Map<String, Object> result = new HashMap<>();
        String userId = joinedUserDTO.getUserId();

        // 데이터베이스에서 JoinedUser 엔티티 조회
        JoinedUser joinedUser = joinedUserRepository.findByRoomIdAndUserId(roomId, userId);

        if (joinedUser != null) {
            // 상태를 변경하는 비즈니스 메서드 호출
            System.out.println("상태 변경 전: " + joinedUser.getJoinedStatus());
            joinedUser.markAsPatched(); // 상태를 변경하는 메서드 호출
            System.out.println("상태 변경 후: " + joinedUser.getJoinedStatus());

            // 엔티티 상태를 데이터베이스에 저장
            joinedUserRepository.save(joinedUser);

            result.put("result", true);
        } else {
            result.put("result", false);
        }
        return result;
    }
}
