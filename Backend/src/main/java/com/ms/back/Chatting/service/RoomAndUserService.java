package com.ms.back.Chatting.service;

import com.ms.back.Chatting.dto.JoinedUserDTO;
import com.ms.back.Chatting.dto.RoomDTO;
import com.ms.back.Chatting.entity.JoinedUser;
import com.ms.back.Chatting.entity.Room;
import com.ms.back.Chatting.repository.JoinedUserRepository;
import com.ms.back.Chatting.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.HashMap;
import java.util.Map;

@Service
public class RoomAndUserService {

    private final RoomRepository roomRepository;

    private final JoinedUserRepository joinedUserRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public RoomAndUserService(RoomRepository roomRepository, JoinedUserRepository joinedUserRepository, ModelMapper modelMapper) {
        this.roomRepository = roomRepository;
        this.joinedUserRepository = joinedUserRepository;
        this.modelMapper = modelMapper;
    }

    /** 방과 유저 함께 등록 */
    @Transactional
    public Map<String, Object> createRoomAndUser(RoomDTO roomDTO, JoinedUserDTO joinedUserDTO) {

        Map<String, Object> response = new HashMap<>();

        try {

            Room roomEntity = modelMapper.map(roomDTO, Room.class);
            roomRepository.save(roomEntity);

            // 사용자 등록 시 방 ID 설정
            joinedUserDTO.setRoodId(roomEntity.getRoomId());

            JoinedUser userEntity = modelMapper.map(joinedUserDTO, JoinedUser.class);
            joinedUserRepository.save(userEntity);


            // 성공 응답 반환
            response.put("result", true);

        } catch (Exception e) {
            // 예외 처리
            response.put("result", false);
        }

        return response;
    }

    /** 방 / 유저 동시 조회 */
    public Map<String, Object> selectRoomAndUser(int roomId) {

        Map<String, Object> response = new HashMap<>();

        try {

            roomRepository.findById(roomId);
            joinedUserRepository.findById(roomId);

            // 성공 응답 반환
            response.put("result", true);

        } catch (Exception e) {
            // 예외 처리
            response.put("result", false);
        }

        return response;

    }
}
