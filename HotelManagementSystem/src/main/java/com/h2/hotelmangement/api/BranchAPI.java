package com.h2.hotelmangement.api;

import com.h2.hotelmangement.common.util.ResponseTemplate;
import com.h2.hotelmangement.entity.Branch;
import com.h2.hotelmangement.model.dto.BranchDTO;
import com.h2.hotelmangement.model.mapper.BranchMapper;
import com.h2.hotelmangement.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_API;

@CrossOrigin(origins = "http://localhost:3454")
@RestController
@RequestMapping(PREFIX_API+"/branch")
public class BranchAPI {

    @Autowired
    private BranchService branchService;

    private BranchMapper branchMapper = new BranchMapper();

    @GetMapping()
    public ResponseEntity<List<BranchDTO>> getAllBranch(){
        List<BranchDTO> branchDTOList = new ArrayList<>();
        List<Branch> branchList = branchService.getAllBranch();
        branchDTOList = branchMapper.convertListBranchEntityToDto(branchList);
        return new ResponseEntity<>(branchDTOList, HttpStatus.OK);
    }

    @GetMapping("/{code}")
    public ResponseEntity<BranchDTO> getBranchByBranchCode(@PathVariable("code") String branchCode){
        Branch branch = branchService.getBranchByBranchCode(branchCode);
        BranchDTO branchDTO = branchMapper.convertEntityToDto(branch);
        return new ResponseEntity<>(branchDTO,HttpStatus.OK);
    }

    @GetMapping("/location")
    public ResponseTemplate getListAllLocation(){
        ResponseTemplate response = new ResponseTemplate();
        Optional<Set<String>> listLocation = branchService.getListLocation();
        if(listLocation.isPresent()){
          response.setData("listLocation", listLocation.get());
        }
        return response;
    }
}
