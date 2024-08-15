package com.ms.back.Chatting.service;

import com.ms.back.Chatting.dto.JoinedUserDTO;
import com.ms.back.Chatting.dto.RoomAndUserDTO;
import com.ms.back.Chatting.entity.JoinedUser;
import com.ms.back.Chatting.entity.Room;
import com.ms.back.Chatting.repository.JoinedUser.JoinedUserRepository;
import com.ms.back.Chatting.repository.JoinedUser.JoinedUserRepositoryCustom;
import com.ms.back.Chatting.repository.Room.RoomRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomAndUserService {

    private final RoomRepository roomRepository;

    private final JoinedUserRepository joinedUserRepository;

    private final JoinedUserRepositoryCustom joinedUserRepositoryCustom;

    private final ModelMapper modelMapper;

    @Autowired
    public RoomAndUserService(RoomRepository roomRepository, JoinedUserRepository joinedUserRepository, ModelMapper modelMapper,
                              JoinedUserRepositoryCustom joinedUserRepositoryCustom) {
        this.roomRepository = roomRepository;
        this.joinedUserRepository = joinedUserRepository;
        this.modelMapper = modelMapper;
        this.joinedUserRepositoryCustom = joinedUserRepositoryCustom;
    }

    /** 방과 유저 함께 등록 */
    @Transactional
    public Map<String, Object> createRoomAndUser(RoomAndUserDTO roomAndUserDTO) {

        Map<String, Object> response = new HashMap<>();

        try {
            // Room 엔티티 생성 및 저장
            Room roomEntity = modelMapper.map(roomAndUserDTO.getRoomDTO(), Room.class);
            roomRepository.save(roomEntity); // Room 저장 후 ID가 설정됨

            // Room ID를 가져옵니다.
            int roomId = roomEntity.getRoomId();

            // JoinedUser 엔티티 생성 및 저장
            JoinedUserDTO joinedUserDTO = roomAndUserDTO.getJoinedUserDTO();
            JoinedUser userEntity = new JoinedUser(
                    roomEntity,
                    joinedUserDTO.getUserId(),
                    joinedUserDTO.getJoinedStatus(),
                    joinedUserDTO.getCreatedAt()
            );

            joinedUserRepository.save(userEntity); // JoinedUser 저장

            // 성공 응답 반환, roomId 포함
            response.put("result", true);
            response.put("roomId", roomId);

        } catch (Exception e) {
            // 예외 처리
            response.put("result", false);
        }

        return response;
    }


    /** 회원에 해당하는 방 전체 리스트 조회 */
    public List<Room> selectRoomsByUserId(String userId) {

        List<Room> rooms = joinedUserRepositoryCustom.findRoomsByUserId(userId);

        return rooms;
    }
}
