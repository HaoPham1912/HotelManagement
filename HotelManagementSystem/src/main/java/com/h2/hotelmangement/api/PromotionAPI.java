package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.Promotion;
import com.h2.hotelmangement.model.dto.PromotionDTO;
import com.h2.hotelmangement.model.mapper.PromotionMapper;
import com.h2.hotelmangement.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PromotionAPI {

    @Autowired
    private PromotionService promotionService;

    public PromotionMapper promotionMapper = new PromotionMapper();

    @GetMapping("/promo")
    public ResponseEntity<List<Promotion>> getAllPromo(){
        return new ResponseEntity<>(promotionService.findAllPromotion(), HttpStatus.OK);
    }

    @PostMapping("/promo")
    public ResponseEntity<Promotion> addPromotion(@RequestBody PromotionDTO promotionDTO){
        System.out.println(promotionDTO.toString());
        Promotion promotion = promotionMapper.converDtoToEntity(promotionDTO);
        System.out.println(promotion.toString());

        promotionService.save(promotion);
        return new ResponseEntity<>(promotion, HttpStatus.OK);
    }

}
