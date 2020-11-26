package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.Bill;
import com.h2.hotelmangement.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class BillAPI {

    @Autowired
    private BillService billService;

    @GetMapping("/bill")
    public ResponseEntity<List<Bill>> getAllBill(){

        return new ResponseEntity<>(billService.getAllBill(), HttpStatus.OK);
    }
}
