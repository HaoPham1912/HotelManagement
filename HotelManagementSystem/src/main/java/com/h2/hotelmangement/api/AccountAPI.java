package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.Account;
import com.h2.hotelmangement.model.dto.AccountDTO;
import com.h2.hotelmangement.model.mapper.AccountMapper;
import com.h2.hotelmangement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3454")
@RestController
@RequestMapping("/api")
public class AccountAPI {

    @Autowired
    private AccountService accountService;

    private AccountMapper accountMapper = new AccountMapper();

    @GetMapping("/account")
    public ResponseEntity<List<AccountDTO>> getAllAccount(){
        List<Account> accountList = accountService.findAll();
        List<AccountDTO> accountDTOList = accountMapper.convertListEntityToDto(accountList);
        return new ResponseEntity<>(accountDTOList, HttpStatus.OK);
    }

    @PostMapping("/account")
    public ResponseEntity<HttpStatus> getAccountByUsername(@RequestBody Account account){
        System.out.println(account.getUsername());
        System.out.println(account.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
