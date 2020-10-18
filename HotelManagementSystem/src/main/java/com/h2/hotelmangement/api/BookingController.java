package com.h2.hotelmangement.api;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class BookingController {

    @GetMapping("/booking")
    public void countNumberOfBooking(@RequestParam(name = "startDate") String startDate,
                                         @RequestParam(name = "endDate") String endDate){
        System.out.println("param1 " +startDate + "params 2 "+ endDate);
    }
}
