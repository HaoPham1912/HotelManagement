package com.h2.hotelmangement.service;

import com.h2.hotelmangement.entity.Promotion;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PromotionService {

    List<Promotion> findAllPromotion();

    void save(Promotion promotion);

    void delete(Long id);

    Page<Promotion> findAllPagePromotion(int pageNo, int pageSize);

    Page<Promotion> findAddPromoPaging(String customerType, int pageNo, int pageSize);

    Promotion getPromotionById(Long id);
}
