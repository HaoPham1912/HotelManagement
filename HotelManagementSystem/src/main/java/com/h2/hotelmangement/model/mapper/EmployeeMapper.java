package com.h2.hotelmangement.model.mapper;

import com.h2.hotelmangement.entity.Account;
import com.h2.hotelmangement.entity.Branch;
import com.h2.hotelmangement.entity.Employee;
import com.h2.hotelmangement.model.dto.AccountDTO;
import com.h2.hotelmangement.model.dto.EmployeeDTO;
import com.h2.hotelmangement.service.AccountService;
import com.h2.hotelmangement.service.BranchService;
import com.h2.hotelmangement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeMapper {

    @Autowired
    private BranchService branchService;

    @Autowired
    private AccountService accountService;

    public EmployeeDTO empEntityToDto(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(employee.getEmployeeId().toString());
        employeeDTO.setEmpName(employee.getName());
        employeeDTO.setEmployeeCode(employee.getEmpCode());
        employeeDTO.setEmpIdCard(employee.getIdCard());
        employeeDTO.setEmpPhone(employee.getPhone());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setUsername(employee.getAccountEmp().getUsername());
        employeeDTO.setBranchCode(employee.getEmpBranch().getBranchCode());
        employeeDTO.setUsername(employee.getAccountEmp().getUsername());
        employeeDTO.setPassword(employee.getAccountEmp().getPassword());
        employeeDTO.setStatus(String.valueOf(employee.getStatusEmp()));
        return employeeDTO;
    }

    public List<EmployeeDTO> listEmpEntityToDto(List<Employee> employeeList) {
        List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
        for (Employee employee : employeeList) {
            EmployeeDTO employeeDTO = empEntityToDto(employee);
            employeeDTOList.add(employeeDTO);
        }
        return employeeDTOList;
    }

    public Employee empDtoToEntity(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setEmployeeId(Long.valueOf(employeeDTO.getEmployeeId()));
        employee.setEmpCode(employeeDTO.getBranchCode());
        employee.setIdCard(employeeDTO.getEmpIdCard());
        employee.setName(employeeDTO.getEmpName());
        employee.setPhone(employeeDTO.getEmpPhone());
        employee.setEmail(employeeDTO.getEmail());
        Branch branch = branchService.getBranchByBranchCode(employeeDTO.getBranchCode());
        employee.setEmpBranch(branch);

        return employee;
    }
}
