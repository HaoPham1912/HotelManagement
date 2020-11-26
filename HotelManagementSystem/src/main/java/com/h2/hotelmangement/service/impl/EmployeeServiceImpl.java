package com.h2.hotelmangement.service.impl;

import com.h2.hotelmangement.entity.Employee;
import com.h2.hotelmangement.repository.EmployeeRepository;
import com.h2.hotelmangement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> findEmpByBranchCode(String branchCode) {
        return employeeRepository.getEmployeeByEmpBranch_BranchCode(branchCode);
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
