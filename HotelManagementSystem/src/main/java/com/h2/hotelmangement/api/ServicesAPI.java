package com.h2.hotelmangement.api;


import com.h2.hotelmangement.entity.Promotion;
import com.h2.hotelmangement.entity.Services;
import com.h2.hotelmangement.model.dto.ServicesDTO;
import com.h2.hotelmangement.model.mapper.ServiceMapper;

import com.h2.hotelmangement.service.ServiceHotel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_API;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(PREFIX_API)
public class ServicesAPI {

    private ServiceMapper serviceMapper = new ServiceMapper();

    @Autowired
    private ServiceHotel servicesService;

    @GetMapping("/services")
    public ResponseEntity<Map<String, Object>> getAllService(@RequestParam(required = false) String name,
                                                             @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                             @RequestParam(value = "size", defaultValue = "3") int size){
        try{
            List<Services> servicesList = new ArrayList<>();
            Page<Services> servicesPage;
            if(name == null){
                servicesPage = servicesService.findPageSerives(pageNo, size);
            }else{
                servicesPage = servicesService.findPageServicesByName(name,pageNo,size);
            }
            servicesList = servicesPage.getContent();
            List<ServicesDTO> servicesDTOList = serviceMapper.convertListServiceEntityToDto(servicesList);
            Map<String, Object> response = new HashMap<>();
            response.put("services", servicesDTOList);
            response.put("currentPage", servicesPage.getNumber());
            response.put("totalItems", servicesPage.getTotalElements());
            response.put("totalPages", servicesPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/services")
    public Long addNewServices(@RequestBody ServicesDTO servicesDTO){

        Services services = serviceMapper.convertServiceDTOtoEntity(servicesDTO);

        System.out.println(services.toString());
        servicesService.save(services);

        return services.getServicesId();
    }

    @GetMapping("/services/{id}")
    public ResponseEntity<ServicesDTO> getServicesByCode(@PathVariable("id") String id){
        Long serviceId = Long.valueOf(id);
        Services services = servicesService.getServicesById(serviceId);
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

    @PutMapping("/services/{id}")
    public ResponseEntity<HttpStatus> updatePromotion(@PathVariable String id,@RequestBody ServicesDTO servicesDTO){
        Long serviceId = Long.valueOf(id);
        Services servicesCurrent = servicesService.getServicesById(serviceId);
        if(servicesCurrent !=null){
            servicesCurrent.setServiceCode(servicesDTO.getServiceCode());
            servicesCurrent.setPrice(Double.valueOf(servicesDTO.getPrice()));
            servicesCurrent.setName(servicesDTO.getName());
            servicesCurrent.setDescription(servicesDTO.getDescription());

            servicesService.save(servicesCurrent);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/all-service")
    public ResponseEntity<List<ServicesDTO>> getAllServiceNoPaging(){
        List<Services> servicesList = servicesService.findAllService();
        List<ServicesDTO> servicesDTOList = serviceMapper.convertListServiceEntityToDto(servicesList);
        return new ResponseEntity<>(servicesDTOList, HttpStatus.OK);
    }
}
