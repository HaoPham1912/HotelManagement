package com.h2.hotelmangement.repository;

import com.h2.hotelmangement.entity.Branch;
import com.h2.hotelmangement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    List<Branch> findBranchByAddress(String address);

    Branch findBranchByBranchCode(String branchCode);
}
