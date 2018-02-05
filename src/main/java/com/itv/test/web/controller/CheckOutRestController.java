package com.itv.test.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itv.test.beans.Quote;
import com.itv.test.web.service.CheckOutService;
import com.itv.test.web.service.ProductService;

@RestController
@RequestMapping("checkout")
public class CheckOutRestController {

	@Autowired
	private CheckOutService checkOutService;
	@Autowired
	private ProductService productService;

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public CheckOutService getCheckOutService() {
		return checkOutService;
	}

	public void setCheckOutService(CheckOutService checkOutService) {
		this.checkOutService = checkOutService;
	}

	@RequestMapping(value = "/generateQuote", method = RequestMethod.POST, produces = "application/json")
	public Quote getQuote(@RequestBody final List<String> productIds) {
		return checkOutService.getQuote(productIds.stream().map(productId -> productService.findByProductId(productId)).collect(Collectors.toList()));
	}
	
}