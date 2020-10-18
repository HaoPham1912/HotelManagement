package com.h2.hotelmangement.service.impl;

import com.h2.hotelmangement.entity.Bed;
import com.h2.hotelmangement.repository.BedRepository;
import com.h2.hotelmangement.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BedServiceImpl implements BedService {

    @Autowired
    private BedRepository bedRepository;

    @Override
    public List<Bed> findAllBed() {
        return bedRepository.findAll();
    }

    @Override
    public void save(Bed bed) {
        bedRepository.save(bed);
    }

    @Override
    public void delete(Long id) {
        bedRepository.deleteById(id);
    }
}
