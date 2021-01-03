package com.h2.hotelmangement.service.impl;

import com.h2.hotelmangement.common.util.ModelMapperUtil;
import com.h2.hotelmangement.entity.Bill;
import com.h2.hotelmangement.entity.Customer;
import com.h2.hotelmangement.model.dto.BillDTO;
import com.h2.hotelmangement.repository.BillRepository;
import com.h2.hotelmangement.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Override
    public Optional<Set<BillDTO>> findBillByCustomer(Long customerId) {
        Optional<Set<Bill>> billList = billRepository.findByCustomer_CustomerId(customerId);
        return Optional.ofNullable(ModelMapperUtil.mapAllToSet(billList.get(),BillDTO.class));
    }

    @Override
    public Customer getCustomerByBillId(Long id) throws Exception {
        Bill bill = billRepository.getOne(id);
        System.out.println(bill.toString());
        if(bill != null){
            return bill.getCustomer();
        }else {
            throw new Exception("Can not get bill with" + id);
        }
    }

    @Override
    public void deleteBillById(Long id) {
        billRepository.deleteById(id);
    }

    @Override
    public Bill getBillByBillId(Long id) {
        return billRepository.getOne(id);
    }

}
