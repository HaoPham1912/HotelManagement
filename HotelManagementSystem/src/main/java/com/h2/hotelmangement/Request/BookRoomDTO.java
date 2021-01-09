package com.h2.hotelmangement.Request;

import com.h2.hotelmangement.model.dto.BranchDTO;

import java.util.Date;
import java.util.List;

public class BookRoomDTO {

    Long checkIn;
    Long checkOut;
    String branchCode;
    List<Integer> accountPeopleDTOList;
    int pageNo;
    int pageSize;

    public Long getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Long checkIn) {
        this.checkIn = checkIn;
    }

    public Long getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Long checkOut) {
        this.checkOut = checkOut;
    }

    public List<Integer> getAccountPeopleDTOList() {
        return accountPeopleDTOList;
    }

    public void setAccountPeopleDTOList(List<Integer> accountPeopleDTOList) {
        this.accountPeopleDTOList = accountPeopleDTOList;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
