package com.h2.hotelmangement.service;

import com.h2.hotelmangement.entity.Branch;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BranchService {

    Branch save(Branch branch);

    void deleteBranch(Long id);

    List<Branch> getAllBranch();

    List<Branch> getBranchByLocation(String location);

    Branch getBranchByBranchCode(String branchCode);

    Optional<Set<String>> getListLocation();
}
