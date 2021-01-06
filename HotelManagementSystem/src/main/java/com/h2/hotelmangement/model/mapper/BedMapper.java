package com.h2.hotelmangement.model.mapper;

import com.h2.hotelmangement.entity.Bed;
import com.h2.hotelmangement.entity.Room;
import com.h2.hotelmangement.model.dto.BedDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BedMapper {

    public BedDTO bedEntityToDto(Bed bed){
        BedDTO bedDTO = new BedDTO();
        Set<String> roomCodeSet = new HashSet<>();
        bedDTO.setBedId(String.valueOf(bed.getBedId()));
        bedDTO.setName(bed.getName());
        bedDTO.setAmountPeople(String.valueOf(bed.getAmountPeople()));
        bedDTO.setPrice(String.valueOf(bed.getPrice()));
        for (Room room:
             bed.getRooms()) {
            String str = room.getRoomCode();
            roomCodeSet.add(str);
        }
        bedDTO.setRoomCodeSet(roomCodeSet);
        bedDTO.setDescription(bed.getDescription());
        bedDTO.setStatus(String.valueOf(bed.getStatus()));

        return bedDTO;
    }

    public List<BedDTO> listBedEntityToDto(List<Bed> bedList){
        List<BedDTO> bedDTOList = new ArrayList<>();
        for (Bed bed:
                bedList) {
            BedDTO bedDTO = bedEntityToDto(bed);
            bedDTOList.add(bedDTO);
        }
        return bedDTOList;
    }

    public Bed convetBedDTOToEntity(BedDTO bedDTO){
        Bed bed = new Bed();
        bed.setName(bedDTO.getName());
        bed.setPrice(Double.valueOf(bedDTO.getPrice()));
        bed.setAmountPeople(Integer.valueOf(bedDTO.getAmountPeople()));
        bed.setDescription(bedDTO.getDescription());

        return bed;
    }
}
