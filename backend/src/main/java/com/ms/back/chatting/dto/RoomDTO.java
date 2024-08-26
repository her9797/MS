package com.ms.back.chatting.dto;

import com.ms.back.chatting.entity.GroupStatus;
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
