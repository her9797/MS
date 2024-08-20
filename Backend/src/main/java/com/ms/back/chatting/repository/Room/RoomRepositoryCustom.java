package com.ms.back.chatting.repository.Room;

import com.ms.back.chatting.dto.RoomAndUserDTO;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepositoryCustom {

    Optional<RoomAndUserDTO> findRoomDetailById(int roomId);


}
