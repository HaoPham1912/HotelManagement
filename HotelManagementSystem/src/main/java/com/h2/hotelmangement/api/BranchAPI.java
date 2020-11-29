package com.h2.hotelmangement.api;


import com.h2.hotelmangement.entity.Branch;
import com.h2.hotelmangement.model.dto.BranchDTO;
import com.h2.hotelmangement.model.mapper.BranchMapper;
import com.h2.hotelmangement.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3454")
@RestController
@RequestMapping("/api")
public class BranchAPI {

    @Autowired
    private BranchService branchService;

    private BranchMapper branchMapper = new BranchMapper();

    @GetMapping("/branch")
    public ResponseEntity<List<BranchDTO>> getAllBranch(){
        List<BranchDTO> branchDTOList = new ArrayList<>();
        List<Branch> branchList = branchService.getAllBranch();
        branchDTOList = branchMapper.convertListBranchEntityToDto(branchList);
        return new ResponseEntity<>(branchDTOList, HttpStatus.OK);
    }

}
