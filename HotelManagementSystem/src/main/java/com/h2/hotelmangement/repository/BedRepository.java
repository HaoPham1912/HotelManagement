package com.h2.hotelmangement.repository;

import com.h2.hotelmangement.entity.Bed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BedRepository extends PagingAndSortingRepository<Bed, Long> {
    Page<Bed> findAllByNameContains(String name, Pageable pageable);
}
