package com.h2.hotelmangement.repository;

import com.h2.hotelmangement.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, String> {
}
