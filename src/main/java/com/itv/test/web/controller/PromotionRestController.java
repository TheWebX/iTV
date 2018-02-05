package com.itv.test.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itv.test.beans.Promotion;
import com.itv.test.web.service.PromotionService;

@RestController
@RequestMapping("promotions")
public class PromotionRestController {

	@Autowired
	private PromotionService promotionService;

	public PromotionService getPromotionService() {
		return promotionService;
	}

	public void setPromotionService(PromotionService promotionService) {
		this.promotionService = promotionService;
	}

	@RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = "application/json")
	public List<Promotion> findAll() {
		return promotionService.findAll();
	}

	@RequestMapping(value = "/findByProductId/{productId}", method = RequestMethod.GET, produces = "application/json")
	public Promotion findByProductId(@PathVariable("productId") String productId) {
		return promotionService.findByProductId(productId);
	}
}