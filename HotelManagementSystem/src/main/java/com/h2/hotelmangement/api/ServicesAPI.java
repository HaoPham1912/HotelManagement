package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.Services;
import com.h2.hotelmangement.model.dto.ServicesDTO;
import com.h2.hotelmangement.model.mapper.ServiceMapper;
import com.h2.hotelmangement.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class ServicesAPI {

    @Autowired
    private ServicesService servicesService;

    @GetMapping("/services")
    public ResponseEntity<List<ServicesDTO>> getAllService(){
        List<ServicesDTO> servicesDTOList = new ArrayList<>();
        ServiceMapper serviceMapper = new ServiceMapper();
        List<Services> servicesList = servicesService.findAllService();
        for (Services services :
                servicesList) {
            ServicesDTO servicesDTO = serviceMapper.serviceEntityToDTO(services);
            servicesDTOList.add(servicesDTO);
        }
        return new ResponseEntity<>(servicesDTOList, HttpStatus.OK);
    }

    @PostMapping("services")
    public Long addNewServices(@RequestBody Services services){
        servicesService.save(services);
        return services.getServicesId();
    }
}
