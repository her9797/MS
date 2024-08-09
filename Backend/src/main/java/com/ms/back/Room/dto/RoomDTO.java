package com.ms.back.Room.dto;

import com.ms.back.Room.entity.GroupStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomDTO {

    private int roomId;

    private GroupStatus groupStatus;

}
