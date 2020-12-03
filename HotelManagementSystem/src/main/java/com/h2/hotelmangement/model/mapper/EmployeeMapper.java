package com.h2.hotelmangement.model.mapper;

import com.h2.hotelmangement.entity.Employee;
import com.h2.hotelmangement.model.dto.EmployeeDTO;

import java.util.ArrayList;
import java.util.List;

public class EmployeeMapper {

    public EmployeeDTO empEntityToDto(Employee employee){
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
        return employeeDTO;
    }

    public List<EmployeeDTO> listEmpEntityToDto(List<Employee> employeeList){
        List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
        for (Employee employee: employeeList) {
            EmployeeDTO employeeDTO = empEntityToDto(employee);
            employeeDTOList.add(employeeDTO);
        }
        return employeeDTOList;
    }
}
