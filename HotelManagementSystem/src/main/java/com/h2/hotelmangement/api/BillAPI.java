package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.Bill;
import com.h2.hotelmangement.model.dto.BillDTO;
import com.h2.hotelmangement.model.mapper.BillMapper;
import com.h2.hotelmangement.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_API;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(PREFIX_API)
@RestController
public class BillAPI {

    @Autowired
    private BillService billService;

    private BillMapper billMapper = new BillMapper();

    @GetMapping("/bill")
    public ResponseEntity<List<BillDTO>> getAllBill(){
        List<Bill> billList = billService.getAllBill();
        return new ResponseEntity<>(billMapper.convertListBillEntityToDto(billList), HttpStatus.OK);
    }

    @PostMapping("/bill")
    public Bill addBill(@RequestBody Bill bill){
        return billService.save(bill);
    }
}
