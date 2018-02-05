package com.itv.test.web.service;

import java.util.List;

import com.itv.test.beans.Product;

public interface ProductService  {
    
	/**
	 * Get all the Products
	 * 
	 * @return products
	 */
    List<Product> findAll();
	
    /**
     * Get a specific Product by ID
     * 
     * @param productId
     * @return
     */
    Product findByProductId(String productId);
}
