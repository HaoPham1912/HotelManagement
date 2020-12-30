package com.h2.hotelmangement.service;

import com.h2.hotelmangement.entity.CancelPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CancelPolicyService {
    List<CancelPolicy> findAll();

    CancelPolicy save(CancelPolicy cancelPolicy);

    CancelPolicy getById(int id);

    Page<CancelPolicy> getPagePolicy(int pageNo, int pageSize);

    Page<CancelPolicy> getPagePolicyByCode(String code, int pageNo, int pageSize);
}
