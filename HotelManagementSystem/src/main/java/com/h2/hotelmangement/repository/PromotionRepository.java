package com.h2.hotelmangement.repository;

import com.h2.hotelmangement.entity.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    Page<Promotion> findAllByCusTypeContains(String customerType, Pageable pageable);
}
