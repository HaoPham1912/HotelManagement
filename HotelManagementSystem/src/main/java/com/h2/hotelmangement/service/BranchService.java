package com.h2.hotelmangement.service;

import com.h2.hotelmangement.entity.Branch;

import java.util.List;

public interface BranchService {

    Branch save(Branch branch);

    void deleteBranch(Long id);

    List<Branch> getAllBranch();
}
