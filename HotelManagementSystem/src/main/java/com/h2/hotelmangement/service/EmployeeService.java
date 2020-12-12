package com.h2.hotelmangement.service;

import com.h2.hotelmangement.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAllEmployee();

    List<Employee> findEmpByBranchCode(String branchCode);

    Employee findEmpById(Long id);

    Employee save(Employee employee);

    void delete(Long id);

    Employee findEmpByAccountId(Long id);
}
