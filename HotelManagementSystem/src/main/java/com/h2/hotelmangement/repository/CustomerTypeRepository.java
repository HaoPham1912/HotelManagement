package com.h2.hotelmangement.repository;

import com.h2.hotelmangement.entity.Customer;
import com.h2.hotelmangement.entity.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerTypeRepository extends JpaRepository<CustomerType, Long> {
    CustomerType findCustomerTypeByTypeName(String typeName);
}
