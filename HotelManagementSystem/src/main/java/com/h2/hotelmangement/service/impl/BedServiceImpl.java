package com.h2.hotelmangement.service.impl;

import com.h2.hotelmangement.entity.Bed;
import com.h2.hotelmangement.repository.BedRepository;
import com.h2.hotelmangement.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BedServiceImpl implements BedService {

    @Autowired
    private BedRepository bedRepository;

    @Override
    public Page<Bed> findAllBed(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Bed> pageResult = bedRepository.findAll(paging);
        return pageResult;
    }

    @Override
    public void save(Bed bed) {
        bedRepository.save(bed);
    }

    @Override
    public void delete(Long id) {
        bedRepository.deleteById(id);
    }

    @Override
    public Page<Bed> findAllBedByName(String name, int pageNo, int pageSize) {
       Pageable pageable = PageRequest.of(pageNo, pageSize);
       Page<Bed> bedPage = bedRepository.findByName(name, pageable);
       return bedPage;
    }

}
