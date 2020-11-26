package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.Bed;
import com.h2.hotelmangement.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BedAPI {

    @Autowired
    private BedService bedService;

    @GetMapping("/bed")
    public ResponseEntity<List<Bed>> getAllBed(){
        return new ResponseEntity<>(bedService.findAllBed(), HttpStatus.OK);
    }

    @PostMapping("/bed")
    public void addBed(@RequestBody Bed bed){
        bedService.save(bed);
    }
}
