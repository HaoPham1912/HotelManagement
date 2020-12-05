package com.h2.hotelmangement.service.impl;

import com.h2.hotelmangement.entity.CancelPolicy;
import com.h2.hotelmangement.repository.CancelPolicyRepository;
import com.h2.hotelmangement.service.CancelPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
