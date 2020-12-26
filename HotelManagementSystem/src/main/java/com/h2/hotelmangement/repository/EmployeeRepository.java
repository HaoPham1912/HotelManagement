package com.h2.hotelmangement.repository;

import com.h2.hotelmangement.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> getEmployeeByEmpBranch_BranchCode(String branchCode);

    Employee getEmployeeByAccountEmp_AccountId(Long id);

    Page<Employee> findAllByNameContains(String name, Pageable pageable);
}
