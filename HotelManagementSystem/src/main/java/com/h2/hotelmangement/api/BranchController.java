package com.h2.hotelmangement.api;


import com.h2.hotelmangement.entity.Branch;
import com.h2.hotelmangement.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
