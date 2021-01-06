package com.h2.hotelmangement.service;

import com.h2.hotelmangement.entity.Bill;
import com.h2.hotelmangement.entity.Customer;
import com.h2.hotelmangement.model.dto.BillDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BillService {
    List<Bill> getAllBill();

    Bill save (Bill bill);

    Page<Bill> findPageBill(int pageNo, int pageSize);

    Page<Bill> findPageBillByCusCode(String customerCode, int pageNo, int pageSize);

    Optional<Set<BillDTO>> findBillByCustomer(Long CustomerId);

    Customer getCustomerByBillId(Long id) throws Exception;

    void deleteBillById(Long id);

    Bill getBillByBillId(Long id);
}
