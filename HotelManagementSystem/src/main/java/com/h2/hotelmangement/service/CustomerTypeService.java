package com.h2.hotelmangement.service;

import com.h2.hotelmangement.entity.CustomerType;

import java.util.List;

public interface CustomerTypeService {

    List<CustomerType> findAllCustomerType();

    CustomerType findCustomerTypeByName(String typeName);
}
