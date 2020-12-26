package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.*;

import com.h2.hotelmangement.model.dto.CustomerDTO;
import com.h2.hotelmangement.model.dto.EmployeeDTO;
import com.h2.hotelmangement.model.mapper.EmployeeMapper;
import com.h2.hotelmangement.service.AccountService;
import com.h2.hotelmangement.service.BranchService;
import com.h2.hotelmangement.service.EmployeeService;
import com.h2.hotelmangement.service.RoleService;
import org.jboss.weld.context.http.Http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_API;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(PREFIX_API)
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
    public ResponseEntity<Map<String, Object>> getAllEmployee(@RequestParam(required = false) String name,
                                                              @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                              @RequestParam(value = "size", defaultValue = "3") int size) {
        try {
            List<Employee> employeeList = new ArrayList<Employee>();
            Page<Employee> employeePage;
            if (name == null) {
                employeePage = employeeService.findAllEmployee(pageNo, size);
            } else {
                employeePage = employeeService.findAllEmployeeByName(name, pageNo, size);
            }
            employeeList = employeePage.getContent();
            System.out.println(employeeList.get(0).toString());
            List<EmployeeDTO> employeeDTOList = employeeMapper.listEmpEntityToDto(employeeList);
            Map<String, Object> response = new HashMap<>();
            response.put("employees", employeeDTOList);
            response.put("currentPage", employeePage.getNumber());
            response.put("totalItems", employeePage.getTotalElements());
            response.put("totalPages", employeePage.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
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

         accountService.save(account);
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
