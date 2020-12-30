package com.h2.hotelmangement.model.mapper;

import com.h2.hotelmangement.entity.Room;
import com.h2.hotelmangement.model.dto.RoomDTO;

import java.util.ArrayList;
import java.util.List;

public class RoomMapper {

    public RoomDTO roomEntityToDto(Room room){
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomId(room.getRoomId());
        roomDTO.setDescription((room.getDescription()));
        roomDTO.setName(room.getName());
        roomDTO.setRoomCode(room.getRoomCode());
        roomDTO.setPrice(room.getPrice());
        roomDTO.setBranchCode(room.getRoomBranch().getBranchCode());
        roomDTO.setThumbnailList(room.getThumbnailsRoomList());
        roomDTO.setPolicyCode(room.getCancelPolicy().getCode());
        roomDTO.setAmmountPeople(String.valueOf(room.getAmountPeople()));
        return roomDTO;
    }


    public List<RoomDTO> convertListRoomEntityToDto(List<Room> roomList){
        List<RoomDTO>  roomDTOList = new ArrayList<>();
        for (Room room :
                roomList) {
            RoomDTO roomDTO = roomEntityToDto(room);
            roomDTOList.add(roomDTO);
        }
        return roomDTOList;
    }
}
