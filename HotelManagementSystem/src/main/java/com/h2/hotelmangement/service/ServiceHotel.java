package com.h2.hotelmangement.service;

import com.h2.hotelmangement.entity.Services;
import com.h2.hotelmangement.model.dto.ServicesDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ServiceHotel {
    List<Services> findAllService();

    void save(Services services);

    void deleteService(Long id) throws Exception;

    Services findServiceByCode(String serviceCode);

    Page<Services> findPageSerives(int pageNo, int pageSize);

    Page<Services> findPageServicesByName(String name, int pageNo, int pageSize);

    Optional<Set<ServicesDTO>> findServiceByRoom(Long roomId);

    Services getServicesById(Long id);
}
