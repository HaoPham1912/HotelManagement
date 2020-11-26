package com.h2.hotelmangement.model.mapper;

import com.h2.hotelmangement.entity.Room;
import com.h2.hotelmangement.entity.Services;
import com.h2.hotelmangement.model.dto.BaseDTO;
import com.h2.hotelmangement.model.dto.ServicesDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ServiceMapper extends BaseDTO {
    public ServicesDTO serviceEntityToDTO(Services services){
        ServicesDTO servicesDTO = new ServicesDTO();
        Set<Long> rommIdSet = new HashSet<>();
        for (Room room:
                services.getRooms()) {
            rommIdSet.add(room.getRoomId());
        }
        servicesDTO.setCreateBy(services.getCreateBy());
        servicesDTO.setCreateDate(services.getCreatedDate());
        servicesDTO.setModifiedBy(services.getModifiedBy());
        servicesDTO.setModifiedDate(services.getModifiedDate());
        servicesDTO.setServiceId(services.getServicesId());
        servicesDTO.setName(services.getName());
        servicesDTO.setServiceCode(services.getServiceCode());
        servicesDTO.setPrice(services.getPrice());
        servicesDTO.setDescription(services.getDescription());
        servicesDTO.setRoomIdSet(rommIdSet);

        return servicesDTO;
    }

    public List<ServicesDTO> convertListServiceEntityToDto(List<Services> servicesList){
        List<ServicesDTO> servicesDTOList = new ArrayList<>();
        for (Services services :
                servicesList) {
            ServicesDTO servicesDTO = serviceEntityToDTO(services);
            servicesDTOList.add(servicesDTO);
        }
        return servicesDTOList;
    }
}
