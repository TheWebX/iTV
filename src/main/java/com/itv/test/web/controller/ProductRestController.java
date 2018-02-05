package com.itv.test.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itv.test.beans.Product;
import com.itv.test.web.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductRestController {

	@Autowired
	private ProductService ProductService;

	public ProductService getProductService() {
		return ProductService;
	}

	public void setProductService(ProductService productService) {
		ProductService = productService;
	}

	@RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = "application/json")
	public List<Product> findAll() {
		return ProductService.findAll();
	}

	@RequestMapping(value = "/findByProductId/{productId}", method = RequestMethod.GET, produces = "application/json")
	public Product findByProductId(@PathVariable("productId") String productId) {
		return ProductService.findByProductId(productId);
	}
}