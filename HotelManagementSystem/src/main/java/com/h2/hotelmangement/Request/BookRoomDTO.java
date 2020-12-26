package com.h2.hotelmangement.Request;

import com.h2.hotelmangement.model.dto.BranchDTO;

import java.util.Date;
import java.util.List;

public class BookRoomDTO {

    Date checkIn;
    Date checkOut;
    BranchDTO branch;
    List<AccountPeopleDTO> accountPeopleDTOList;

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public BranchDTO getBranch() {
        return branch;
    }

    public void setBranch(BranchDTO branch) {
        this.branch = branch;
    }

    public List<AccountPeopleDTO> getAccountPeopleDTOList() {
        return accountPeopleDTOList;
    }

    public void setAccountPeopleDTOList(List<AccountPeopleDTO> accountPeopleDTOList) {
        this.accountPeopleDTOList = accountPeopleDTOList;
    }
}
