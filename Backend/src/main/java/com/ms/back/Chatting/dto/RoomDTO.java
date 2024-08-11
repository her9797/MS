package com.ms.back.Chatting.dto;

import com.ms.back.Chatting.entity.GroupStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomDTO {

    private int roomId;

    private GroupStatus groupStatus;

    public RoomDTO(GroupStatus groupStatus) {
        this.groupStatus = groupStatus;
    }
}
