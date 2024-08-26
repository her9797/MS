package com.ms.back.chatting.service;

import com.ms.back.chatting.dto.JoinedUserDTO;
import com.ms.back.chatting.dto.RoomAndUserDTO;
import com.ms.back.chatting.entity.JoinedUser;
import com.ms.back.chatting.entity.Room;
import com.ms.back.chatting.repository.JoinedUser.JoinedUserRepository;
import com.ms.back.chatting.repository.JoinedUser.JoinedUserRepositoryCustom;
import com.ms.back.chatting.repository.Room.RoomRepository;
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
    @Transactional(rollbackOn = { IllegalArgumentException.class, RuntimeException.class })
    public Map<String, Object> createRoomAndUser(RoomAndUserDTO roomAndUserDTO) {
        Map<String, Object> response = new HashMap<>();

        try {
            // JoinedUser 엔티티 생성 및 저장
            List<JoinedUserDTO> joinedUserDTOList = roomAndUserDTO.getJoinedUserDTO();

            // joinedUserDTO가 1개 이하일 때 예외 처리
            if (joinedUserDTOList.size() <= 1) {
                throw new IllegalArgumentException("혼자서는 채팅을 할 수 없죠?");
            }

            // Room 엔티티 생성 및 저장
            Room roomEntity = modelMapper.map(roomAndUserDTO.getRoomDTO(), Room.class);
            roomRepository.save(roomEntity); // roomId 저장

            // Room ID 가져오기
            int roomId = roomEntity.getRoomId();

            for (JoinedUserDTO joinedUserDTO : joinedUserDTOList) {
                JoinedUser userEntity = new JoinedUser(
                        roomId, // Room 엔티티 참조
                        joinedUserDTO.getUserId(),
                        joinedUserDTO.getJoinedStatus(),
                        joinedUserDTO.getCreatedAt()
                );
                joinedUserRepository.save(userEntity); // JoinedUser 저장
            }

            // 성공 응답 반환
            response.put("result", true);

        } catch (Exception e) {
            // 예외 처리 및 트랜잭션 롤백
            response.put("result", false);
            response.put("error", "오류가 발생했습니다: " + e.getMessage());
            e.printStackTrace();
        }

        return response;
    }



    /** 회원에 해당하는 방 전체 리스트 조회 */
    public List<Room> selectRoomsByUserId(String userId) {

        List<Room> rooms = joinedUserRepositoryCustom.findRoomsByUserId(userId);

        return rooms;
    }
}
