package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.Account;
import com.h2.hotelmangement.entity.Employee;
import com.h2.hotelmangement.entity.Role;
import com.h2.hotelmangement.model.dto.AccountDTO;
import com.h2.hotelmangement.model.dto.EmployeeDTO;
import com.h2.hotelmangement.model.mapper.AccountMapper;
import com.h2.hotelmangement.service.AccountService;
import com.h2.hotelmangement.service.EmployeeService;
import com.h2.hotelmangement.service.RoleService;
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
public class AccountAPI {

    @Autowired
    private AccountService accountService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RoleService roleService;

    private AccountMapper accountMapper = new AccountMapper();

    @GetMapping("/account")
    public ResponseEntity<Map<String, Object>> getAllAccount(@RequestParam(required = false) String username,
                                                             @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                             @RequestParam(value = "size", defaultValue = "3") int size) {
        try {
            List<Account> accountList = new ArrayList<>();
            Page<Account> accountPage;
            if (username == null) {
                accountPage = accountService.findAll(pageNo, size);
            } else {
                accountPage = accountService.getAllAccountPagination(username, pageNo, size);
            }
            accountList = accountPage.getContent();
            List<AccountDTO> accountDTOList = accountMapper.convertListEntityToDto(accountList);
            Map<String, Object> response = new HashMap<>();
            response.put("accounts", accountDTOList);
            response.put("currentPage", accountPage.getNumber());
            response.put("totalItems", accountPage.getTotalElements());
            response.put("totalPages", accountPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable String id) {
        Long accId = Long.valueOf(id);
        Optional<Account> account = accountService.getAccountByAccountId(accId);
        if (account.isPresent()) {
            Account response = account.get();
            AccountDTO accountDTO = accountMapper.convertEntityToDto(response);
            return new ResponseEntity<>(accountDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/account")
    public ResponseEntity<HttpStatus> getAccountByUsername(@RequestBody AccountDTO account) {
        Account acc = new Account();
        acc.setUsername(account.getUsername());
        acc.setPassword(account.getPassword());
        Set<Role> roleSet = new HashSet<>();
        for (String s :
                account.getRoleName()) {
            Role role = roleService.findByRoleName(s);
            roleSet.add(role);
        }
        acc.setRoles(roleSet);
        accountService.save(acc);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //only for testing
    @PutMapping("/account/{id}")
    public ResponseEntity<HttpStatus> updateStatusAccount(@PathVariable("id") String id, @RequestBody AccountDTO accountDTO) {
        System.out.println("dsasdasdasd");
        Long idAcc = Long.valueOf(id);
        Optional<Account> account = accountService.getAccountByAccountId(idAcc);
        if (account.isPresent()) {
            Account account1 = account.get();
            account1.setUsername(accountDTO.getUsername());
            account1.setPassword(accountDTO.getPassword());
            accountService.save(account1);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<HttpStatus> disablleAccount(@PathVariable String id) {
        Long accId = Long.valueOf(id);
        Optional<Account> account = accountService.getAccountByAccountId(accId);
        if (account.isPresent()) {
            try {
                accountService.delete(accId);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
