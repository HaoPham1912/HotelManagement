package com.h2.hotelmangement.service.impl;

import com.h2.hotelmangement.common.util.ModelMapperUtil;
import com.h2.hotelmangement.entity.Branch;
import com.h2.hotelmangement.model.dto.BranchDTO;
import com.h2.hotelmangement.repository.BranchRepository;
import com.h2.hotelmangement.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import java.util.*;
import java.util.stream.Collectors;


@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public Branch save(Branch branch) {
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

    @Override
    public Optional<List<BranchDTO>> getBranchByLocation(String location) {
        Optional<Set<Branch>> listBranch = branchRepository.findBranchByLocationLike(location);
        if(listBranch.isPresent()){
            return Optional.ofNullable(ModelMapperUtil.mapAll(listBranch.get(),BranchDTO.class));
        }
        return Optional.empty();
    }

//    @Override
//    public List<Branch> getBranchByLocation(String address) {
//        return branchRepository.findBranchByAddress(address);
//    }


    @Override
    public Branch getBranchByBranchCode(String branchCode) {
        return branchRepository.findBranchByBranchCode(branchCode);
    }

    @Override
    public Optional<Set<String>> getListLocation() {
        Set<String> listLocation = new HashSet<>();
        List<Branch> listBranch = getAllBranch();
        if (!CollectionUtils.isEmpty(listBranch)) {
            listLocation = listBranch.stream().map(branch -> branch.getLocation()).collect(Collectors.toSet());
        }
        return Optional.ofNullable(listLocation);
    }

    public Page<Branch> getAllBranchPage(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return branchRepository.findAll(pageable);
    }

    @Override
    public Page<Branch> getAllBranchPageByName(String name, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return branchRepository.findAllByBranchCodeContains(name, pageable);

    }

    @Override
    public Branch getBranchById(Long id) {
        return branchRepository.getOne(id);
    }
}
