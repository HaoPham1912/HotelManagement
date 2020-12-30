package com.h2.hotelmangement.service;

import com.h2.hotelmangement.entity.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    Page<Employee> findAllEmployee(int pageNo, int pageSize);

    Page<Employee> findAllEmployeeByName(String name, int pageNo, int pageSize);

    List<Employee> findEmpByBranchCode(String branchCode);

    Employee findEmpById(Long id);

    Employee save(Employee employee);

    void delete(Long id) throws Exception;

    Employee findEmpByAccountId(Long id);
}
