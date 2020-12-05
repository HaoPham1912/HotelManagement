package com.h2.hotelmangement.service;

import com.h2.hotelmangement.entity.CancelPolicy;

import java.util.List;

public interface CancelPolicyService {
    List<CancelPolicy> findAll();
}
