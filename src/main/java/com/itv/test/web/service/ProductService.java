package com.itv.test.web.service;

import java.util.List;

import com.itv.test.beans.Product;

public interface ProductService  {
    
    List<Product> findAll();
	
    Product findByProductId(String productId);
}
