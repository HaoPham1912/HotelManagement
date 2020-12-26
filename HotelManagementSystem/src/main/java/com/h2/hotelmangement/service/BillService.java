package com.h2.hotelmangement.service;

import com.h2.hotelmangement.entity.Bill;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BillService {
    List<Bill> getAllBill();

    Bill save (Bill bill);

    Page<Bill> findPageBill(int pageNo, int pageSize);

    Page<Bill> findPageBillByCusCode(String customerCode, int pageNo, int pageSize);
}
