package com.h2.hotelmangement.api;


import com.h2.hotelmangement.entity.Branch;
import com.h2.hotelmangement.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @PostMapping(value = "/branch")
    public String addBranch(@RequestBody Branch branch){
        branchService.save(branch);
        return "index";
    }

    @GetMapping("/branch")
    public ResponseEntity<List<Branch>> getBranchByLocation(@RequestParam(name = "address") String address){
        System.out.println(address);
        List<Branch> branchList = branchService.getBranchByLocation(address);

        return new ResponseEntity<>(branchList, HttpStatus.OK);
    }

}
