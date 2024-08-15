package com.ms.back.Chatting.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomAndUserDTO {

    // 채팅 방과 유저를 동시에 등록하기 위해 만든 CLASS

    private RoomDTO roomDTO;


    private List<JoinedUserDTO> joinedUserDTO;    // 한명이 아니기 때문에 List


}
