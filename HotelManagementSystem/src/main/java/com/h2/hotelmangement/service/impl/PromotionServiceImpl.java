package com.h2.hotelmangement.service.impl;

import com.h2.hotelmangement.entity.Promotion;
import com.h2.hotelmangement.repository.PromotionRepository;
import com.h2.hotelmangement.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Override
    public List<Promotion> findAllPromotion() {
        return promotionRepository.findAll();
    }

    @Override
    public void save(Promotion promotion) {
        promotionRepository.save(promotion);
    }

    @Override
    public void delete(Long id) {
        promotionRepository.deleteById(id);
    }
}
