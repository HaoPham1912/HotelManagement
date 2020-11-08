package com.h2.hotelmangement.model.mapper;

import com.h2.hotelmangement.entity.Room;
import com.h2.hotelmangement.model.dto.RoomDTO;

public class RoomMapper {

    public RoomDTO roomEntityToDto(Room room){
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomId(room.getRoomId());
        roomDTO.setDescription((room.getDescription()));
        roomDTO.setName(room.getName());
        roomDTO.setRoomCode(room.getRoomCode());
        roomDTO.setPrice(room.getPrice());
        roomDTO.setBranchId(room.getRoomBranch().getBranchId());
        roomDTO.setThumbnailList(room.getThumbnailsRoomList());
        return roomDTO;
    }
}
