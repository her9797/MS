package com.ms.back.Chatting.service;

import com.ms.back.Chatting.dto.RoomDTO;
import com.ms.back.Chatting.entity.Room;
import com.ms.back.Chatting.repository.RoomRepository;
import com.ms.back.Notice.entity.Notice;
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

    private final ModelMapper modelMapper;

    @Autowired
    public RoomService(RoomRepository roomRepository, ModelMapper modelMapper) {
        this.roomRepository = roomRepository;
        this.modelMapper = modelMapper;
    }

    /** 방 생성 */
    public Map<String, Object> insertRoom(RoomDTO roomDTO) {

        // 방이 생성될 때, JoinedUser 함께 생성되어야함

        Map<String, Object> result = new HashMap<>();

        try {
            Room roomEntity = modelMapper.map(roomDTO, Room.class);
            roomRepository.save(roomEntity);

            result.put("result", true);
        } catch (Exception e) {

            log.error(e.getMessage());
            result.put("result", false);
        }
        return result;

    }
}
