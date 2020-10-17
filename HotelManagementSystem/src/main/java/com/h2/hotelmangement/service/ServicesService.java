package com.h2.hotelmangement.service;

import com.h2.hotelmangement.entity.Services;

import java.util.List;

public interface ServicesService {

    List<Services> findAllService();

    void saveOrUpdate(Services services);

    void deleteService(Long id);
}
