package com.itv.test.web.repository;

import java.util.List;

import com.itv.test.web.repository.dto.Promotion;

public interface PromotionRepository  {
    
    List<Promotion> findAll();
	
    Promotion findByProductId(String productId);
}
