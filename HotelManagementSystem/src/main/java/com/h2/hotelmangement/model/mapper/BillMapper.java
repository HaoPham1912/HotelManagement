package com.h2.hotelmangement.model.mapper;

import com.h2.hotelmangement.entity.Bill;
import com.h2.hotelmangement.model.dto.BillDTO;

import java.util.ArrayList;
import java.util.List;

public class BillMapper {

    public BillDTO convertBillEntityToDTO(Bill bill){
        BillDTO billDTO = new BillDTO();
        billDTO.setBillId(bill.getBillid().toString());
        billDTO.setCreateDate(bill.getCreatedate().toString());
        billDTO.setPromoCode(bill.getPromocode());
        billDTO.setTotalPrice(String.valueOf(bill.getTotalprice()));
        billDTO.setCustomerCode(bill.getCustomer().getCusCode());
        billDTO.setCustomerName(bill.getCustomer().getName());
        return billDTO;
    }

    public List<BillDTO> convertListBillEntityToDto(List<Bill> billList){
        List<BillDTO> billDTOList = new ArrayList<>();
        for (Bill bill:
             billList) {
            BillDTO billDTO = convertBillEntityToDTO(bill);
            billDTOList.add(billDTO);
        }
        return billDTOList;
    }
}
