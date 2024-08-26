package com.ms.back.chatting.service;

import com.ms.back.chatting.dto.MsgDTO;
import com.ms.back.chatting.entity.Message;
import com.ms.back.chatting.repository.MsgRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MsgService {

    private final MsgRepository msgRepository;

    private final ModelMapper modelMapper;


    @Autowired
    public MsgService(MsgRepository msgRepository, ModelMapper modelMapper) {
        this.msgRepository = msgRepository;
        this.modelMapper = modelMapper;
    }


    /** 메시지 등록 */
    public Map<String , Object> insertMsg(MsgDTO msgDTO) {

        Map<String, Object> result = new HashMap<>();

        try {
            Message messageEntity = modelMapper.map(msgDTO, Message.class);
            msgRepository.save(messageEntity);

            result.put("result", true);
        } catch (Exception e) {

            log.error(e.getMessage());
            result.put("result", false);
        }
        return result;


    }

    public List<Message> selectMsg(int roomId) {

        return msgRepository.findMessageByRoomId(roomId);
    }
}
