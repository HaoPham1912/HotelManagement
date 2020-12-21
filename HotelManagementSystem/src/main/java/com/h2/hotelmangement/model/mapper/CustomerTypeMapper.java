package com.h2.hotelmangement.model.mapper;

import com.h2.hotelmangement.entity.Customer;
import com.h2.hotelmangement.entity.CustomerType;
import com.h2.hotelmangement.model.dto.CustomerDTO;
import com.h2.hotelmangement.model.dto.CustomerTypeDTO;
import com.h2.hotelmangement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerTypeMapper {

    public CustomerMapper customerMapper = new CustomerMapper();

    public CustomerTypeDTO convertEntityToDto(CustomerType customerType){
        CustomerTypeDTO customerTypeDTO = new CustomerTypeDTO();
        customerTypeDTO.setTypeName(customerType.getTypeName());
//
//        List<CustomerDTO> customerDTOList = new ArrayList<>();
//        for (Customer customer:
//             customerType.getCustomerSet()) {
//            CustomerDTO customerDTO = customerMapper.customerEntityToDto(customer);
//            customerDTOList.add(customerDTO);
//        }
//        customerTypeDTO.setCustomerSet(customerDTOList);

        return customerTypeDTO;
    }

    public List<CustomerTypeDTO> convertListCusTypeEntityToDTO(List<CustomerType> customerTypeList){
        List<CustomerTypeDTO> customerTypeDTOList = new ArrayList<>();
        for (CustomerType customerType :
                customerTypeList) {
            CustomerTypeDTO customerTypeDTO = convertEntityToDto(customerType);
            customerTypeDTOList.add(customerTypeDTO);
        }
        return customerTypeDTOList;
    }

}
