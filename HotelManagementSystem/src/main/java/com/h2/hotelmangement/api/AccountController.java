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

@RestController
@RequestMapping("/")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/account")
    public ResponseEntity<List<AccountDTO>> getAllAccount(){
        AccountMapper accountMapper = new AccountMapper();
        List<AccountDTO> accountDTOList = new ArrayList<>();
        List<Account> accountList = accountService.findAll();
        for (Account account:
             accountList) {
            AccountDTO accountDTO = accountMapper.convertEntityToDto(account);
            accountDTOList.add(accountDTO);
        }
        return new ResponseEntity<>(accountDTOList, HttpStatus.OK);
    }


}
