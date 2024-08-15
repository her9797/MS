package com.ms.back.Chatting.service;

import com.ms.back.Chatting.dto.RoomAndUserDTO;
import com.ms.back.Chatting.dto.RoomDTO;
import com.ms.back.Chatting.entity.Room;
import com.ms.back.Chatting.repository.Room.RoomRepository;
import com.ms.back.Chatting.repository.Room.RoomRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class RoomService {

    private final RoomRepository roomRepository;

    private final RoomRepositoryCustom roomRepositoryCustom;

    private final ModelMapper modelMapper;

    @Autowired
    public RoomService(RoomRepository roomRepository, ModelMapper modelMapper, RoomRepositoryCustom roomRepositoryCustom) {
        this.roomRepository = roomRepository;
        this.modelMapper = modelMapper;
        this.roomRepositoryCustom = roomRepositoryCustom;
    }


    public RoomAndUserDTO selectRoomDetail(int roomId) {
        return roomRepositoryCustom.findRoomDetailById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with id " + roomId));
    }
}
