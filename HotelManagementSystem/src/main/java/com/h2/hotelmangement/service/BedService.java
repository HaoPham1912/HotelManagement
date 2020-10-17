package com.h2.hotelmangement.service;

import com.h2.hotelmangement.entity.Bed;

import java.util.List;

public interface BedService {

    List<Bed> findAllBed();

    void createOrUpdate(Bed bed);

    void delete(Long id);
}
