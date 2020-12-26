package com.h2.hotelmangement.service;

import com.h2.hotelmangement.entity.Bed;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BedService {

    Page<Bed> findAllBed(int pageNo, int pageSize);

    void save(Bed bed);

    void delete(Long id);

    Page<Bed> findAllBedByName(String name, int pageNo, int pageSize);
}
