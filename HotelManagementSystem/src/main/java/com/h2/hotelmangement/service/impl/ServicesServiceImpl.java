package com.h2.hotelmangement.service.impl;

import com.h2.hotelmangement.entity.Services;
import com.h2.hotelmangement.repository.ServiceRepository;
import com.h2.hotelmangement.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ServicesServiceImpl implements ServicesService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<Services> findAllService() {
        return serviceRepository.findAll();
    }

    @Override
    public void save(Services services) {
        serviceRepository.save(services);
    }

    @Override
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
}

