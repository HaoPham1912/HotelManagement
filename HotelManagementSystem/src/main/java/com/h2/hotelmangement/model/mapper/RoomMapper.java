package com.h2.hotelmangement.model.mapper;

import com.google.api.client.util.Lists;
import com.h2.hotelmangement.entity.*;
import com.h2.hotelmangement.model.dto.BedDTO;
import com.h2.hotelmangement.model.dto.RoomDTO;
import com.h2.hotelmangement.model.dto.ServicesDTO;
import com.h2.hotelmangement.service.BranchService;
import com.h2.hotelmangement.service.CancelPolicyService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RoomMapper {

    public RoomDTO roomEntityToDto(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        BedMapper bedMapper = new BedMapper();
        ServiceMapper serviceMapper = new ServiceMapper();

        roomDTO.setRoomId(room.getRoomId());
        roomDTO.setDescription((room.getDescription()));
        roomDTO.setName(room.getName());
        roomDTO.setRoomCode(room.getRoomCode());
        roomDTO.setPrice(room.getPrice());
        roomDTO.setBranchCode(room.getRoomBranch().getBranchCode());
        roomDTO.setThumbnailList(room.getThumbnailsRoomList());
        roomDTO.setPolicyCode(room.getCancelPolicy().getCode());
        roomDTO.setMainImage(room.getMainImage());
        roomDTO.setAmmountPeople(String.valueOf(room.getAmountPeople()));
        Set<Bed> bedSet = room.getBedSet();
        List<Bed> bedList = Lists.newArrayList(bedSet);
        List<BedDTO> bedDTOList = bedMapper.listBedEntityToDto(bedList);
        roomDTO.setBedDTOList(bedDTOList);
        Set<Services> servicesSet = room.getServices();
        List<Services> servicesList = Lists.newArrayList(servicesSet);
        List<ServicesDTO>servicesDTOList = serviceMapper.convertListServiceEntityToDto(servicesList);
        roomDTO.setServicesDTOList(servicesDTOList);
        return roomDTO;
    }


    public List<RoomDTO> convertListRoomEntityToDto(List<Room> roomList) {
        List<RoomDTO> roomDTOList = new ArrayList<>();
        for (Room room :
                roomList) {
            RoomDTO roomDTO = roomEntityToDto(room);
            roomDTOList.add(roomDTO);
        }
        return roomDTOList;
    }

    public Room convertRoomDtoToEntity(RoomDTO roomDTO) {
        Room room = new Room();
        room.setRoomCode(roomDTO.getRoomCode());
        room.setAmountPeople(Integer.parseInt(roomDTO.getAmmountPeople()));
        System.out.println(roomDTO.getPolicyCode());
        room.setName(roomDTO.getName());
        room.setPrice(roomDTO.getPrice());
        room.setMainImage(roomDTO.getMainImage());
        room.setDescription(roomDTO.getDescription());
        return room;
    }
}
