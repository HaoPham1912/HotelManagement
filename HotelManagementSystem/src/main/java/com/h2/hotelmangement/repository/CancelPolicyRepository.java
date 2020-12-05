package com.h2.hotelmangement.repository;

import com.h2.hotelmangement.entity.CancelPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancelPolicyRepository extends JpaRepository<CancelPolicy, Integer> {
}
