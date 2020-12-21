package com.h2.hotelmangement.service;

import com.h2.hotelmangement.entity.Services;

import java.util.List;

public interface ServicesService {

    List<Services> findAllService();

    void save(Services services);

    void deleteService(Long id) throws Exception;

    Services findServiceByCode(String serviceCode);
}
