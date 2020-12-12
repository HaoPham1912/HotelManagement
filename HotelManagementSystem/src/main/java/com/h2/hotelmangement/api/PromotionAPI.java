package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.Promotion;
import com.h2.hotelmangement.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3454")
public class PromotionAPI {

    @Autowired
    private PromotionService promotionService;

    @GetMapping("/promo")
    public ResponseEntity<List<Promotion>> getAllPromo(){
        return new ResponseEntity<>(promotionService.findAllPromotion(), HttpStatus.OK);
    }
}
