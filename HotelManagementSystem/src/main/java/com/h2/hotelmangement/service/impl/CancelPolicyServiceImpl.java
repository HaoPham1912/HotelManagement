package com.h2.hotelmangement.service.impl;

import com.h2.hotelmangement.entity.CancelPolicy;
import com.h2.hotelmangement.repository.CancelPolicyRepository;
import com.h2.hotelmangement.service.CancelPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CancelPolicyServiceImpl implements CancelPolicyService {

    @Autowired
    private CancelPolicyRepository cancelPolicyRepository;

    @Override
    public List<CancelPolicy> findAll() {
        return cancelPolicyRepository.findAll();
    }

    @Override
    public CancelPolicy save(CancelPolicy cancelPolicy) {
        return cancelPolicyRepository.save(cancelPolicy);
    }

    @Override
    public CancelPolicy getById(int id) {
        return cancelPolicyRepository.getOne(id);
    }

    @Override
    public Page<CancelPolicy> getPagePolicy(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        return cancelPolicyRepository.findAll(pageable);
    }

    @Override
    public Page<CancelPolicy> getPagePolicyByCode(String code, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        return cancelPolicyRepository.findAllByCodeContains(code, pageable);
    }
}
