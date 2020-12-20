package com.h2.hotelmangement.model.mapper;

import com.h2.hotelmangement.entity.Room;
import com.h2.hotelmangement.entity.Services;
import com.h2.hotelmangement.model.dto.BaseDTO;
import com.h2.hotelmangement.model.dto.ServicesDTO;

import javax.xml.ws.Service;
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
        servicesDTO.setPrice(String.valueOf(services.getPrice()));
        servicesDTO.setDescription(services.getDescription());
        servicesDTO.setRoomIdSet(rommIdSet);
        servicesDTO.setStatus(services.getStatus());

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

    public Services convertServiceDTOtoEntity(ServicesDTO servicesDTO){
        Services services = new Services();
        services.setServicesId(servicesDTO.getServiceId());
        services.setServiceCode(servicesDTO.getServiceCode());
        services.setName(servicesDTO.getName());
        services.setPrice(Double.parseDouble(servicesDTO.getPrice()));
        services.setDescription(servicesDTO.getDescription());
        services.setStatus(servicesDTO.getStatus());
        return services;
    }
}
