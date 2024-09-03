package com.ms.back.chatting.repository.JoinedUser;

import com.ms.back.chatting.dto.RoomAndUserDTO;
import com.ms.back.chatting.entity.Room;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoinedUserRepositoryCustom {

    List<RoomAndUserDTO> findRoomsByUserId(String userId);


}
