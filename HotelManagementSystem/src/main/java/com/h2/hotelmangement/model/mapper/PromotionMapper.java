package com.h2.hotelmangement.model.mapper;

import com.h2.hotelmangement.entity.Promotion;
import com.h2.hotelmangement.model.dto.PromotionDTO;
import org.springframework.security.core.parameters.P;

public class PromotionMapper {

    public Promotion converDtoToEntity(PromotionDTO promotionDTO){
        Promotion promotion = new Promotion();


        promotion.setPromotionCode(promotionDTO.getPromoCode());
        promotion.setPercent(Float.parseFloat(promotionDTO.getPercent()));
        promotion.setCusType(promotionDTO.getCustomerType());
        promotion.setStartDate(promotionDTO.getStartDate());
        promotion.setEndDate(promotionDTO.getEndDate());

        return promotion;
    }
}
