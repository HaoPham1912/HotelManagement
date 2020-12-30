package com.h2.hotelmangement.api;


import com.h2.hotelmangement.entity.Branch;
import com.h2.hotelmangement.entity.Employee;
import com.h2.hotelmangement.model.dto.BranchDTO;
import com.h2.hotelmangement.model.dto.EmployeeDTO;
import com.h2.hotelmangement.model.mapper.BranchMapper;
import com.h2.hotelmangement.model.mapper.EmployeeMapper;
import com.h2.hotelmangement.service.BranchService;
import com.h2.hotelmangement.service.EmployeeService;
import com.sun.org.apache.bcel.internal.generic.LUSHR;
import jdk.nashorn.internal.ir.BreakableNode;
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
public class BranchAPI {

    @Autowired
    private BranchService branchService;

    @Autowired
    private EmployeeService employeeService;

    private BranchMapper branchMapper = new BranchMapper();

    private EmployeeMapper employeeMapper = new EmployeeMapper();

    @GetMapping("/branch")
    public ResponseEntity<Map<String, Object>> getAllBranch(@RequestParam(required = false) String branchCode,
                                                            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                            @RequestParam(value = "size", defaultValue = "3") int size){
        try{
            List<Branch> branchList = new ArrayList<>();
            Page<Branch> branchPage;
            if(branchCode == null){
                branchPage = branchService.getAllBranchPage(pageNo, size);
            }else{
                branchPage = branchService.getAllBranchPageByName(branchCode,pageNo,size);
            }
            branchList = branchPage.getContent();
            List<BranchDTO> branchDTOList = branchMapper.convertListBranchEntityToDto(branchList);
            Map<String, Object> response = new HashMap<>();
            response.put("branchs", branchDTOList);
            response.put("currentPage", branchPage.getNumber());
            response.put("totalItems", branchPage.getTotalElements());
            response.put("totalPages", branchPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all-branch")
    public ResponseEntity<List<BranchDTO>> getAllBranchNoPaging(){
        List<Branch> branchList = branchService.getAllBranch();
        List<BranchDTO> branchDTOList = branchMapper.convertListBranchEntityToDto(branchList);
        return new ResponseEntity<>(branchDTOList, HttpStatus.OK);
    }

    @GetMapping("/detail-branch/{code}")
    public ResponseEntity<BranchDTO> getBranchByBranchCode(@PathVariable("code") String branchCode){
        BranchDTO branchDTO = new BranchDTO();
        try{
            Branch branch = branchService.getBranchByBranchCode(branchCode);
            if(branch == null) throw new Exception("Can not find Bracnh by "+branchCode);
            else {
                List<Employee> employee = employeeService.findEmpByBranchCode(branchCode);
                List<EmployeeDTO> employeeDTOList = employeeMapper.listEmpEntityToDto(employee);
                branchDTO = branchMapper.convertEntityToDto(branch);
                branchDTO.setEmployeeDTOSet(employeeDTOList);
                return new ResponseEntity<>(branchDTO,HttpStatus.OK);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(branchDTO, HttpStatus.NO_CONTENT);
        }

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
    public ResponseEntity<?> showBranchByLocation(@PathVariable("location") String location) {
        Optional<List<BranchDTO>> listBranch = branchService.getBranchByLocation(location);
        return new ResponseEntity<>(listBranch, HttpStatus.OK);
    }

    @PutMapping("/branch/{code}")
    public ResponseEntity<HttpStatus> updateBranchInfo(@PathVariable String code, @RequestBody BranchDTO branchDTO){
     Branch branch = branchService.getBranchByBranchCode(code);

     if(branch != null){
         branchDTO.setBranchId(String.valueOf(branch.getBranchId()));
        branch = branchMapper.convertDtoToEntity(branchDTO);
        branchService.save(branch);
        return new ResponseEntity<>(HttpStatus.OK);
     }else {
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
     }

    }
}
