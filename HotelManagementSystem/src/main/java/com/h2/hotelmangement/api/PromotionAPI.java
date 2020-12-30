package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.Employee;
import com.h2.hotelmangement.entity.Promotion;
import com.h2.hotelmangement.model.dto.EmployeeDTO;
import com.h2.hotelmangement.model.dto.PromotionDTO;
import com.h2.hotelmangement.model.mapper.PromotionMapper;
import com.h2.hotelmangement.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PromotionAPI {

    @Autowired
    private PromotionService promotionService;

    public PromotionMapper promotionMapper = new PromotionMapper();

    @GetMapping("/promo")
    public ResponseEntity<Map<String, Object>> getAllPromo(@RequestParam(required = false) String customerType,
                                                           @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                           @RequestParam(value = "size", defaultValue = "3") int size){
        try {
            List<Promotion> promotionList = new ArrayList<Promotion>();
            Page<Promotion> promotionPage;
            if (customerType == null) {
                promotionPage = promotionService.findAllPagePromotion(pageNo, size);
            } else {
                promotionPage = promotionService.findAddPromoPaging(customerType, pageNo, size);
            }
            promotionList = promotionPage.getContent();
            System.out.println(promotionList.get(0).toString());
            Map<String, Object> response = new HashMap<>();
            response.put("promos", promotionList);
            response.put("currentPage", promotionPage.getNumber());
            response.put("totalItems", promotionPage.getTotalElements());
            response.put("totalPages", promotionPage.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/promo")
    public ResponseEntity<Promotion> addPromotion(@RequestBody PromotionDTO promotionDTO){
        System.out.println(promotionDTO.toString());
        Promotion promotion = promotionMapper.converDtoToEntity(promotionDTO);
        System.out.println(promotion.toString());

        promotionService.save(promotion);
        return new ResponseEntity<>(promotion, HttpStatus.OK);
    }

    @PutMapping("/promo/{id}")
    public ResponseEntity<HttpStatus> updatePromotion(@PathVariable String id,@RequestBody Promotion promotion){
        Long promoId = Long.valueOf(id);
        Promotion promotionCurrent = promotionService.getPromotionById(promoId);
        if(promotionCurrent !=null){
            promotionCurrent.setPercent(promotion.getPercent());
            promotionCurrent.setCusType(promotion.getCusType());
            promotionCurrent.setStartDate(promotion.getStartDate());
            promotionCurrent.setEndDate(promotion.getEndDate());

            promotionService.save(promotionCurrent);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/promo/{id}")
    public ResponseEntity<PromotionDTO> getPrmomotionByid(@PathVariable String id){
        Long promoId = Long.valueOf(id);
        Promotion promotion = promotionService.getPromotionById(promoId);
        if(promotion != null){
            PromotionDTO promotionDTO = promotionMapper.converEntityToDto(promotion);
            return new ResponseEntity<>(promotionDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/promo/{id}")
    public ResponseEntity<HttpStatus> deletePromotion(@PathVariable String id){
        Long promoId = Long.valueOf(id);
        Promotion promotion = promotionService.getPromotionById(promoId);
        if(promotion!=null){
            promotionService.delete(promoId);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
