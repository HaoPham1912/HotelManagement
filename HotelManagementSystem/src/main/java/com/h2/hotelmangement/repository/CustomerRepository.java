package com.h2.hotelmangement.repository;

import com.h2.hotelmangement.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByCustomerType_TypeName(String typeName);
    Optional<Customer> findByEmail(String email);
}
