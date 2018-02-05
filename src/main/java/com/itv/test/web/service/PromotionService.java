package com.itv.test.web.service;

import java.util.List;

import com.itv.test.beans.Promotion;

public interface PromotionService  {
    
	/**
	 * Get all the Promotions available
	 * 
	 * @return Promotions
	 */
    List<Promotion> findAll();
	
    /**
     * Get a specific Promotion by productId
     * 
     * @param productId
     * @return Promotion
     */
    Promotion findByProductId(String productId);
}
