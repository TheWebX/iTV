package com.itv.test.web.repository;

import java.util.List;

import com.itv.test.web.repository.dto.Product;

public interface ProductRepository  {
    
    List<Product> findAll();
	
    Product findByProductId(String productId);
}
