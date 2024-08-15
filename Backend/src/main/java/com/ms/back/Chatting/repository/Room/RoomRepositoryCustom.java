package com.ms.back.Chatting.repository.Room;

import com.ms.back.Chatting.dto.RoomAndUserDTO;
import com.ms.back.Chatting.service.RoomAndUserService;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepositoryCustom {

    Optional<RoomAndUserDTO> findRoomDetailById(int roomId);


}
