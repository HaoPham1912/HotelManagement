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

import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_API;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(PREFIX_API)
public class ServicesAPI {

    private ServiceMapper serviceMapper = new ServiceMapper();

    @Autowired
    private ServicesService servicesService;

    @GetMapping("/services")
    public ResponseEntity<List<ServicesDTO>> getAllService(){
        List<Services> servicesList = servicesService.findAllService();
        List<ServicesDTO> servicesDTOList = serviceMapper.convertListServiceEntityToDto(servicesList);

        return new ResponseEntity<>(servicesDTOList, HttpStatus.OK);
    }

    @PostMapping("/services")
    public Long addNewServices(@RequestBody ServicesDTO servicesDTO){

        Services services = serviceMapper.convertServiceDTOtoEntity(servicesDTO);

        System.out.println(services.toString());
        servicesService.save(services);

        return services.getServicesId();
    }

    @GetMapping("/services/{code}")
    public ResponseEntity<ServicesDTO> getServicesByCode(@PathVariable("code") String serviceCode){
        Services services = servicesService.findServiceByCode(serviceCode);
        ServicesDTO servicesDTO = serviceMapper.serviceEntityToDTO(services);
        return new ResponseEntity<>(servicesDTO, HttpStatus.OK);
    }

    @DeleteMapping("/services/{id}")
    public ResponseEntity<HttpStatus> deleteService(@PathVariable("id") String id){
        Long serviceId = Long.valueOf(id);
        try {
            servicesService.deleteService(serviceId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
