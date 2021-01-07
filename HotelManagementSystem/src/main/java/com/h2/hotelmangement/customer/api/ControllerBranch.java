package com.h2.hotelmangement.customer.api;

import com.h2.hotelmangement.entity.Branch;
import com.h2.hotelmangement.model.dto.BranchDTO;
import com.h2.hotelmangement.model.mapper.BranchMapper;
import com.h2.hotelmangement.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_API;
import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_CUSTOMER;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(PREFIX_CUSTOMER+PREFIX_API)
public class ControllerBranch {
    @Autowired
    private BranchService branchService;

    private BranchMapper branchMapper = new BranchMapper();

    @GetMapping("/branch/{location}")
    public ResponseEntity<?> showBranchByLocation(@PathVariable("location") String location) {
        Optional<List<BranchDTO>> listBranch = branchService.getBranchByLocation(location);
        return new ResponseEntity<>(listBranch, HttpStatus.OK);
    }

    @GetMapping("/branch/all")
    public ResponseEntity<List<BranchDTO>> getAllBranchNoPaging() {
        List<Branch> branchList = branchService.getAllBranch();
        List<BranchDTO> branchDTOList = branchMapper.convertListBranchEntityToDto(branchList);
        return new ResponseEntity<>(branchDTOList, HttpStatus.OK);
    }

    @GetMapping("/branch/current/{id}")
    public ResponseEntity<BranchDTO> getBranchById(@PathVariable String id){
        Long branchId = Long.valueOf(id);

        Branch branch = branchService.getBranchById(branchId);
        try {
            if(branch == null) throw new Exception("Can not find branch with id "+ id);
            else {
                BranchDTO branchDTO = branchMapper.convertEntityToDto(branch);
                return new ResponseEntity<>(branchDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
