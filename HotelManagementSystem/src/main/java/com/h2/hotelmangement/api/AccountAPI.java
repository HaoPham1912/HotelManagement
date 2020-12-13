package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.Account;
import com.h2.hotelmangement.entity.Role;
import com.h2.hotelmangement.model.dto.AccountDTO;
import com.h2.hotelmangement.model.mapper.AccountMapper;
import com.h2.hotelmangement.service.AccountService;
import com.h2.hotelmangement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_API;

@CrossOrigin(origins = "http://localhost:3454")
@RestController
@RequestMapping(PREFIX_API)
public class AccountAPI {

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    private AccountMapper accountMapper = new AccountMapper();

    @GetMapping("/account")
    public ResponseEntity<List<AccountDTO>> getAllAccount() {
        List<Account> accountList = accountService.findAll();
        List<AccountDTO> accountDTOList = accountMapper.convertListEntityToDto(accountList);
        return new ResponseEntity<>(accountDTOList, HttpStatus.OK);
    }

    @PostMapping("/account")
    public ResponseEntity<HttpStatus> getAccountByUsername(@RequestBody AccountDTO account) {
        Account acc = new Account();
        acc.setUsername(account.getUsername());
        acc.setPassword(account.getPassword());
        Set<Role> roleSet = new HashSet<>();
        for (String s:
             account.getRoleName()) {
            Role role = roleService.findByRoleName(s);
            roleSet.add(role);
        }
        acc.setRoles(roleSet);
        accountService.save(acc);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
