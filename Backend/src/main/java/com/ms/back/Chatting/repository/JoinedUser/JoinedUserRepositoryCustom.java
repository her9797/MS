package com.ms.back.Chatting.repository.JoinedUser;

import com.ms.back.Chatting.entity.JoinedUser;
import com.ms.back.Chatting.entity.Room;

import java.util.List;

public interface JoinedUserRepositoryCustom {

    List<Room> findRoomsByUserId(String userId);


}
