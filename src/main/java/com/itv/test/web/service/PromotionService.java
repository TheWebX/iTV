package com.itv.test.web.service;

import java.util.List;

import com.itv.test.beans.Promotion;

public interface PromotionService  {
    
    List<Promotion> findAll();
	
    Promotion findByProductId(String productId);
}
