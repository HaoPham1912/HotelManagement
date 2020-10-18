package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.Bed;
import com.h2.hotelmangement.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BedController {

    @Autowired
    private BedService bedService;


    @PostMapping("/bed")
    public void addBed(@RequestBody Bed bed){
        bedService.save(bed);
    }
}
