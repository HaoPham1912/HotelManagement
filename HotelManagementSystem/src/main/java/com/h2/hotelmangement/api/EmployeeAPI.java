package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.Account;
import com.h2.hotelmangement.entity.Employee;
import com.h2.hotelmangement.entity.Role;

import com.h2.hotelmangement.model.dto.EmployeeDTO;
import com.h2.hotelmangement.model.mapper.EmployeeMapper;
import com.h2.hotelmangement.service.AccountService;
import com.h2.hotelmangement.service.EmployeeService;
import com.h2.hotelmangement.service.RoleService;
import org.jboss.weld.context.http.Http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_API;

@CrossOrigin(origins = "http://localhost:3454")
@RestController
@RequestMapping(PREFIX_API)
public class EmployeeAPI {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

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

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDTO> getEmpById(@PathVariable("id") Long empId){
        Employee employee = employeeService.findEmpById(empId);
        EmployeeDTO employeeDTO = employeeMapper.empEntityToDto(employee);
        return new ResponseEntity<>(employeeDTO,HttpStatus.OK);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<HttpStatus> deleteEmp(@PathVariable("id") Long id){
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/employee")
    public void addNewEmployee(@RequestBody EmployeeDTO employeeDTO){
        Account account = new Account();
        Role role = roleService.findByRoleName(employeeDTO.getRoleName());
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        account.setUsername(employeeDTO.getUsername());
        account.setPassword(employeeDTO.getPassword());
        account.setRoles(roleSet);
        accountService.save(account);
        System.out.println(employeeDTO.toString());
        System.out.println(role.toString());
    }
}
