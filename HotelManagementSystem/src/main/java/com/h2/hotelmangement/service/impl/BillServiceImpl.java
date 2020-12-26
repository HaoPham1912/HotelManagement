package com.h2.hotelmangement.service.impl;

import com.h2.hotelmangement.entity.Bill;
import com.h2.hotelmangement.repository.BillRepository;
import com.h2.hotelmangement.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Override
    public List<Bill> getAllBill() {
        return billRepository.findAll();
    }

    @Override
    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public Page<Bill> findPageBill(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return billRepository.findAll(pageable);
    }

    @Override
    public Page<Bill> findPageBillByCusCode(String customerCode, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return billRepository.findAllByCustomer_CusCode(customerCode,pageable);
    }
}
