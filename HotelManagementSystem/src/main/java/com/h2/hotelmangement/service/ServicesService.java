package com.h2.hotelmangement.service;

import com.h2.hotelmangement.entity.Services;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ServicesService {

    List<Services> findAllService();

    void save(Services services);

    void deleteService(Long id) throws Exception;

    Services findServiceByCode(String serviceCode);

    Page<Services> findPageSerives(int pageNo, int pageSize);

    Page<Services> findPageServicesByName(String name, int pageNo, int pageSize);
}
