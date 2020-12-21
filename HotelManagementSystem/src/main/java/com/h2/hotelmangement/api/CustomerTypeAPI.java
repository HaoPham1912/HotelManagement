package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.CustomerType;
import com.h2.hotelmangement.model.dto.CustomerTypeDTO;
import com.h2.hotelmangement.model.mapper.CustomerTypeMapper;
import com.h2.hotelmangement.service.CustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_API;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(PREFIX_API)
public class CustomerTypeAPI {

    @Autowired
    CustomerTypeService customerTypeService;

    public CustomerTypeMapper customerTypeMapper = new CustomerTypeMapper();

    @GetMapping("/custype")
    public ResponseEntity<List<CustomerTypeDTO>> getAllCustomerType(){
        List<CustomerType> customerTypeList = customerTypeService.findAllCustomerType();
        List<CustomerTypeDTO> customerTypeDTOList = customerTypeMapper.convertListCusTypeEntityToDTO(customerTypeList);
        return new ResponseEntity<>(customerTypeDTOList, HttpStatus.OK);
    }
}
