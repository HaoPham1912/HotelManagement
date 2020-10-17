package com.h2.hotelmangement.service.impl;

import com.h2.hotelmangement.entity.Bill;
import com.h2.hotelmangement.repository.BillRepository;
import com.h2.hotelmangement.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
