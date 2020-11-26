package com.h2.hotelmangement.api;

import com.h2.hotelmangement.model.dto.EmployeeDTO;
import com.h2.hotelmangement.model.mapper.EmployeeMapper;
import com.h2.hotelmangement.service.EmployeeService;
import org.jboss.weld.context.http.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class EmployeeAPI {

    @Autowired
    private EmployeeService employeeService;

    private EmployeeMapper employeeMapper = new EmployeeMapper();

    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(){
        List<EmployeeDTO> employeeDTOList = employeeMapper.listEmpEntityToDto(employeeService.findAllEmployee());
        return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);
    }

    @GetMapping("/employee/branch")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByBranchCode(@RequestParam String branchCode){
        List<EmployeeDTO> employeeDTOList = employeeMapper.listEmpEntityToDto(employeeService.findEmpByBranchCode(branchCode));

        return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);
    }
}
