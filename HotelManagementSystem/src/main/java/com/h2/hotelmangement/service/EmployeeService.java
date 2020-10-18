package com.h2.hotelmangement.service;

import com.h2.hotelmangement.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAllEmployee();

    void save(Employee employee);

    void delete(Long id);
}
