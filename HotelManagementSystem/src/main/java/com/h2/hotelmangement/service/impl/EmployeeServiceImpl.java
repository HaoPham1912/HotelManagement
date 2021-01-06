package com.h2.hotelmangement.service.impl;

import com.h2.hotelmangement.entity.Employee;
import com.h2.hotelmangement.repository.EmployeeRepository;
import com.h2.hotelmangement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Page<Employee> findAllEmployee(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Page<Employee> findAllEmployeeByName(String name, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return employeeRepository.findAllByNameContains(name, pageable);
    }

    @Override
    public List<Employee> findEmpByBranchCode(String branchCode) {
        return employeeRepository.getEmployeeByEmpBranch_BranchCode(branchCode);
    }

    @Override
    public Employee findEmpById(Long id) {
        return employeeRepository.getOne(id);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(Long id) throws Exception {
        Employee employee = employeeRepository.getOne(id);
        if(employee != null){
            Boolean empStatus = employee.getStatusEmp();
            employee.setStatusEmp(!empStatus);
            employeeRepository.save(employee);
        }else{
            throw new Exception("Can not find employee with id "+ id);
        }

    }

    @Override
    public Employee findEmpByAccountId(Long id) {
        return employeeRepository.getEmployeeByAccountEmp_AccountId(id);
    }

    @Override
    public Employee getEmployeeByUsername(String username) {
        return employeeRepository.getEmployeeByAccountEmp_Username(username);
    }
}
