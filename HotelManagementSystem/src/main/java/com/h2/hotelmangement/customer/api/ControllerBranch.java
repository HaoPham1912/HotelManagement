package com.h2.hotelmangement.customer.api;

import com.h2.hotelmangement.common.util.ResponseTemplate;
import com.h2.hotelmangement.common.util.TokenParser;
import com.h2.hotelmangement.entity.Account;
import com.h2.hotelmangement.entity.Branch;
import com.h2.hotelmangement.entity.Customer;
import com.h2.hotelmangement.model.dto.BranchDTO;
import com.h2.hotelmangement.model.dto.CustomerDTO;
import com.h2.hotelmangement.model.mapper.BranchMapper;
import com.h2.hotelmangement.service.AccountService;
import com.h2.hotelmangement.service.BranchService;
import com.h2.hotelmangement.service.CustomerService;
import com.h2.hotelmangement.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private CustomerService customerService;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    TokenParser tokenParser;


    @PostMapping("/branch/location")
    public ResponseEntity<?> showBranchByLocation(@RequestParam String location) {
        Optional<List<BranchDTO>> listBranch = branchService.getBranchByLocation(location);
        return new ResponseEntity<>(listBranch, HttpStatus.OK);
    }

    @GetMapping("/branch")
    public ResponseEntity<List<BranchDTO>> getAllBranchNoPaging() {
        List<Branch> branchList = branchService.getAllBranch();
        List<BranchDTO> branchDTOList = branchMapper.convertListBranchEntityToDto(branchList);
        return new ResponseEntity<>(branchDTOList, HttpStatus.OK);
    }

    @GetMapping("/branch/{id}")
    public ResponseEntity<BranchDTO> getBranchById(@PathVariable String id) {
        Long branchId = Long.valueOf(id);

        Branch branch = branchService.getBranchById(branchId);
        try {
            if (branch == null) throw new Exception("Can not find branch with id " + id);
            else {
                BranchDTO branchDTO = branchMapper.convertEntityToDto(branch);
                return new ResponseEntity<>(branchDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/branch/alllocation")
    public ResponseEntity<Object> getAllLocation() {
        return new ResponseEntity<>(branchService.getListLocation(), HttpStatus.OK);

    }

    @PostMapping("/auth/google")
    public ResponseTemplate loginwithGoogle(@RequestParam String email) {
        ResponseTemplate responseTemplate = new ResponseTemplate();
        Optional<Customer> customer = customerService.findCustomerByEmail(email);
        if (customer.isPresent()) {
            UserDetails userDetails = userDetailsServiceImpl.loadUserDetails(customer.get().getAccountCus().getUsername(),
                    customer.get().getAccountCus().getPassword());
            System.out.println("User detail " + userDetails.getUsername());

            if (null != userDetails) {
                String token = tokenParser.generateToken(userDetails);
                Map<String, Object> items = new HashMap<>();
                items.put("accessToken", token);
                items.put("user", userDetails.getUsername());
                responseTemplate.setData(items);
                responseTemplate.setStatus(HttpStatus.OK.value());
                return responseTemplate;
            }
            responseTemplate.setStatus(HttpStatus.BAD_REQUEST.value());
            responseTemplate.setData("message", "Email or passsword is correct");
            return responseTemplate;

        }
        responseTemplate.setData("message", "Email is not correct");
        responseTemplate.setStatus(HttpStatus.BAD_REQUEST.value());
        return responseTemplate;

    }
}
