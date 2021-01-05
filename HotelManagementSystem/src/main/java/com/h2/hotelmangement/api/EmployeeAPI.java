package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.*;

import com.h2.hotelmangement.model.dto.AccountDTO;
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
import org.springframework.security.core.parameters.P;
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
        } catch (Exception e) {
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
        try {
            Employee employee = employeeService.findEmpById(empId);
            EmployeeDTO employeeDTO = employeeMapper.empEntityToDto(employee);
            return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<HttpStatus> deleteEmp(@PathVariable("id") String id) {
        Long empId = Long.valueOf(id);
        Employee employee = employeeService.findEmpById(empId);
        if(employee != null){
            try {
                employeeService.delete(empId);
                Account account = employee.getAccountEmp();
                if(account != null){
                    Boolean status = account.getStatus();
                    account.setStatus(!status);
                    accountService.save(account);
                }
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
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
    public ResponseEntity<HttpStatus> updateEmployee(@PathVariable("id") String id, @RequestBody EmployeeDTO employeeDTO) {
        System.out.println(employeeDTO.toString());
        Long idEmp = Long.valueOf(id);
        System.out.println("id emp" + idEmp);

        Employee employee = employeeService.findEmpById(idEmp);

        if (employee != null) {
            employee.setName(employeeDTO.getEmpName());
            employee.setIdCard(employeeDTO.getEmpIdCard());
            employee.setPhone(employeeDTO.getEmpPhone());
            employee.setEmail(employeeDTO.getEmail());
            Branch branch = branchService.getBranchByBranchCode(employeeDTO.getBranchCode());
            if(branch!=null){
                employee.setEmpBranch(branch);
            }else {
                employee.setEmpBranch(null);
            }
            employeeService.save(employee);
            return new ResponseEntity<>( HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/info-emp/{username}")
    public ResponseEntity<EmployeeDTO> getInformationEmp(@PathVariable String username){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Employee employee = employeeService.getEmployeeByUsername(username);
//        System.out.println(employee.toString());
        Account account = accountService.findAccountByUsername(username);
//        System.out.println(account.toString());
        if(employee != null){
            employeeDTO  = employeeMapper.empEntityToDto(employee);
        }
        if(account != null){
            employeeDTO.setUsername(account.getUsername());
            employeeDTO.setPassword(account.getPassword());
        }
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @PutMapping("/info-emp/{username}")
    public ResponseEntity<HttpStatus> updateEmployeeInformation(@PathVariable String username, @RequestBody EmployeeDTO employeeDTO){
        Employee employee = employeeService.getEmployeeByUsername(username);
        System.out.println(employee.toString());
        Account account = accountService.findAccountByUsername(username);
        System.out.println(account.toString());
       try{
           if(employee != null){
               employee.setIdCard(employeeDTO.getEmpIdCard());
               employee.setName(employeeDTO.getEmpName());
               employee.setPhone(employeeDTO.getEmpPhone());
               employee.setEmail(employeeDTO.getEmail());

               employeeService.save(employee);
           }else {
               throw new Exception("Can not get employee with username "+username);
           }
           if(account != null){
               account.setPassword(employeeDTO.getNewPass());
               accountService.save(account);
           }else {
               throw new Exception("Can not get account with username "+username);
           }
           return new ResponseEntity<>(HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
    }
}
