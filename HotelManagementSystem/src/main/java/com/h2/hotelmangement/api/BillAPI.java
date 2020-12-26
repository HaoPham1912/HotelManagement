package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.Bill;
import com.h2.hotelmangement.entity.Services;
import com.h2.hotelmangement.model.dto.BillDTO;
import com.h2.hotelmangement.model.dto.ServicesDTO;
import com.h2.hotelmangement.model.mapper.BillMapper;
import com.h2.hotelmangement.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_API;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(PREFIX_API)
@RestController
public class BillAPI {

    @Autowired
    private BillService billService;

    private BillMapper billMapper = new BillMapper();

    @GetMapping("/bill")
    public ResponseEntity<Map<String, Object>> getAllBill(@RequestParam(required = false) String customerCode,
                                                          @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                          @RequestParam(value = "size", defaultValue = "3") int size){
        try{
            List<Bill> billList = new ArrayList<>();
            Page<Bill> billPage;
            if(customerCode == null){
                billPage = billService.findPageBill(pageNo, size);
            }else{
                billPage = billService.findPageBillByCusCode(customerCode,pageNo,size);
            }
            billList = billPage.getContent();
            List<BillDTO> billDTOList = billMapper.convertListBillEntityToDto(billList);
            Map<String, Object> response = new HashMap<>();
            response.put("bills", billDTOList);
            response.put("currentPage", billPage.getNumber());
            response.put("totalItems", billPage.getTotalElements());
            response.put("totalPages", billPage.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/bill")
    public Bill addBill(@RequestBody Bill bill){
        return billService.save(bill);
    }
}
