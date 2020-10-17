package com.h2.hotelmangement.service;

import com.h2.hotelmangement.entity.Promotion;

import java.util.List;

public interface PromotionService {

    List<Promotion> findAllPromotion();

    void saveOrUpdate(Promotion promotion);

    void delete(Long id);
}
