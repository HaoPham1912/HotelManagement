package com.h2.hotelmangement.model.mapper;

import com.h2.hotelmangement.entity.Account;
import com.h2.hotelmangement.entity.Role;
import com.h2.hotelmangement.model.dto.AccountDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AccountMapper {

    public AccountDTO convertEntityToDto(Account account){
        AccountDTO accountDTO = new AccountDTO();
        Set<String> roleName = new HashSet<>();
        accountDTO.setAccountId(account.getAccountId());
        accountDTO.setUsername(account.getUsername());
        accountDTO.setPassword(account.getPassword());
        accountDTO.setStatus(account.isStatus());
        for (Role role: account.getRoles()) {
            roleName.add(role.getRoleName());
        }
        accountDTO.setRoleName(roleName);
        return accountDTO;
    }

    public List<AccountDTO> convertListEntityToDto(List<Account> accountList){
        List<AccountDTO> accountDTOList = new ArrayList<>();
        for (Account account:
                accountList) {
            AccountDTO accountDTO = convertEntityToDto(account);
            accountDTOList.add(accountDTO);
        }
        return  accountDTOList;
    }
}
