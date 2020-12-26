package com.h2.hotelmangement.api;

import com.h2.hotelmangement.common.util.ResponseTemplate;
import com.h2.hotelmangement.entity.Branch;
import com.h2.hotelmangement.entity.Employee;
import com.h2.hotelmangement.model.dto.BranchDTO;
import com.h2.hotelmangement.model.dto.EmployeeDTO;
import com.h2.hotelmangement.model.mapper.BranchMapper;
import com.h2.hotelmangement.model.mapper.EmployeeMapper;
import com.h2.hotelmangement.service.BranchService;
import com.h2.hotelmangement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_API;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(PREFIX_API)
public class BranchAPI {

    @Autowired
    private BranchService branchService;

    @Autowired
    private EmployeeService employeeService;

    private BranchMapper branchMapper = new BranchMapper();

    private EmployeeMapper employeeMapper = new EmployeeMapper();

    @GetMapping("/branch")
    public ResponseEntity<List<BranchDTO>> getAllBranch(){
        List<BranchDTO> branchDTOList = new ArrayList<>();
        List<Branch> branchList = branchService.getAllBranch();
        branchDTOList = branchMapper.convertListBranchEntityToDto(branchList);
        return new ResponseEntity<>(branchDTOList, HttpStatus.OK);
    }

    @GetMapping("/branch/{code}")
    public ResponseEntity<BranchDTO> getBranchByBranchCode(@PathVariable("code") String branchCode){
        Branch branch = branchService.getBranchByBranchCode(branchCode);
        List<Employee> employee = employeeService.findEmpByBranchCode(branchCode);
        List<EmployeeDTO> employeeDTOList = employeeMapper.listEmpEntityToDto(employee);
        BranchDTO branchDTO = branchMapper.convertEntityToDto(branch);
        branchDTO.setEmployeeDTOSet(employeeDTOList);
        return new ResponseEntity<>(branchDTO,HttpStatus.OK);
    }

    @PostMapping("/branch")
    public ResponseEntity<HttpStatus> addNewBranch(@RequestBody BranchDTO branchDTO){
        System.out.println(branchDTO.toString());
        Branch branch = branchMapper.convertDtoToEntity(branchDTO);
        System.out.println(branch.toString());
        try{

            branchService.save(branch);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @GetMapping("/branch/{location}")
    public ResponseEntity<?> showBranchByLocation(@PathVariable("location") String location){
        Optional<List<BranchDTO>> listBranch = branchService.getBranchByLocation(location);
        return new ResponseEntity<>(listBranch,HttpStatus.OK);
    }

}
