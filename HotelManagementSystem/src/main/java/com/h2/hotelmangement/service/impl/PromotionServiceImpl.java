package com.h2.hotelmangement.service.impl;

import com.h2.hotelmangement.entity.Promotion;
import com.h2.hotelmangement.repository.PromotionRepository;
import com.h2.hotelmangement.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<Promotion> findAllPagePromotion(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return promotionRepository.findAll(pageable);
    }

    @Override
    public Page<Promotion> findAddPromoPaging(String customerType, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return promotionRepository.findAllByCusTypeContains(customerType,pageable);
    }

    @Override
    public Promotion getPromotionById(Long id) {
        return promotionRepository.getOne(id);
    }
}
