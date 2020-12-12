package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.Account;
import com.h2.hotelmangement.entity.Branch;
import com.h2.hotelmangement.entity.Employee;
import com.h2.hotelmangement.entity.Role;

import com.h2.hotelmangement.model.dto.EmployeeDTO;
import com.h2.hotelmangement.model.mapper.EmployeeMapper;
import com.h2.hotelmangement.service.AccountService;
import com.h2.hotelmangement.service.BranchService;
import com.h2.hotelmangement.service.EmployeeService;
import com.h2.hotelmangement.service.RoleService;
import org.jboss.weld.context.http.Http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:3454")
@RestController
@RequestMapping("/api")
public class EmployeeAPI {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BranchService branchService;

    private EmployeeMapper employeeMapper = new EmployeeMapper();

    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
        List<EmployeeDTO> employeeDTOList = employeeMapper.listEmpEntityToDto(employeeService.findAllEmployee());
        return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);
    }

    @GetMapping("/employee/branch")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByBranchCode(@RequestParam String branchCode) {
        List<EmployeeDTO> employeeDTOList = employeeMapper.listEmpEntityToDto(employeeService.findEmpByBranchCode(branchCode));

        return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDTO> getEmpById(@PathVariable("id") Long empId) {
        Employee employee = employeeService.findEmpById(empId);
        EmployeeDTO employeeDTO = employeeMapper.empEntityToDto(employee);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<HttpStatus> deleteEmp(@PathVariable("id") Long id) {
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/employee")
    public void addNewEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Account account = new Account();
        Role role = roleService.findByRoleName("EMPLOYEE");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        account.setUsername(employeeDTO.getUsername());
        account.setPassword(employeeDTO.getPassword());
        account.setRoles(roleSet);
        accountService.save(account);
        //accountService.save(account);
        Employee employee = new Employee();
        employee.setEmpCode(employeeDTO.getEmployeeCode());
        employee.setIdCard(employeeDTO.getEmpIdCard());
        employee.setPhone(employeeDTO.getEmpPhone());
        employee.setName(employeeDTO.getEmpName());
        employee.setEmail(employeeDTO.getEmail());

        Branch branch = branchService.getBranchByBranchCode(employeeDTO.getBranchCode());

        employee.setEmpBranch(branch);
        employee.setAccountEmp(account);

        employeeService.save(employee);

        System.out.println(employeeDTO.toString());


    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id") String id, @RequestBody EmployeeDTO employeeDTO) {
        Long idEmp = Long.valueOf(id);
        System.out.println("id emp" + idEmp);

        Employee employee = employeeService.findEmpById(idEmp);

        if (employee != null) {
            employee.setName(employeeDTO.getEmpName());
            employee.setEmail(employeeDTO.getEmail());
            employee.setPhone(employeeDTO.getEmpPhone());
            employee.setIdCard(employeeDTO.getEmpIdCard());
        }
        Employee employee1 = employeeService.save(employee);
        return new ResponseEntity<>(employeeMapper.empEntityToDto(employee1), HttpStatus.NO_CONTENT);
    }
}
