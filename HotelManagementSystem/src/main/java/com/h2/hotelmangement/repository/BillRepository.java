package com.h2.hotelmangement.repository;

import com.h2.hotelmangement.entity.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    Page<Bill> findAllByCustomer_CusCode(String customerCode, Pageable pageable);

    Optional<Set<Bill>>findByCustomer_CustomerId(Long customerID);
}
