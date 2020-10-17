package com.h2.hotelmangement.repository;

import com.h2.hotelmangement.entity.Bed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BedRepository extends JpaRepository<Bed, Long> {
}
