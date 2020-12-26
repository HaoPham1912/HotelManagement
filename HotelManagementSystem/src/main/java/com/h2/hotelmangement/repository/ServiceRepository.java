package com.h2.hotelmangement.repository;

import com.h2.hotelmangement.entity.Services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ServiceRepository extends JpaRepository<Services, Long> {
    Services findByServiceCode(String serviceCode);

    Page<Services> findAllByNameContains(String name, Pageable pageable);

    Optional<Set<Services>> findByRooms(Long roomId);
}
