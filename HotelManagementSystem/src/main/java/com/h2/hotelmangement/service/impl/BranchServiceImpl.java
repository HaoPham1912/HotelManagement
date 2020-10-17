package com.h2.hotelmangement.service.impl;

import com.h2.hotelmangement.entity.Branch;
import com.h2.hotelmangement.repository.BranchRepository;
import com.h2.hotelmangement.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public Branch addBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    @Override
    public Branch updateBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    @Override
    public void deleteBranch(Long id) {
         branchRepository.deleteById(id);
    }

    @Override
    public List<Branch> getAllBranch() {
        return branchRepository.findAll();
    }
}
