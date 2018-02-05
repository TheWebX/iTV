package com.itv.test.web.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itv.test.beans.Promotion;
import com.itv.test.web.repository.ProductRepository;
import com.itv.test.web.repository.PromotionRepository;
import com.itv.test.web.repository.mapper.Mapper;
import com.itv.test.web.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {

	@Autowired
	private PromotionRepository promotionRepository;

	@Autowired
	private ProductRepository productRepository;

	public ProductRepository getProductRepository() {
		return productRepository;
	}

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Autowired
	private Mapper<Promotion, com.itv.test.web.repository.dto.Promotion> mapper;

	public PromotionRepository getPromotionRepository() {
		return promotionRepository;
	}

	public void setPromotionRepository(PromotionRepository promotionRepository) {
		this.promotionRepository = promotionRepository;
	}

	public Mapper<Promotion, com.itv.test.web.repository.dto.Promotion> getMapper() {
		return mapper;
	}

	public void setMapper(Mapper<Promotion, com.itv.test.web.repository.dto.Promotion> mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<Promotion> findAll() {
		return promotionRepository.findAll().stream()
		.map(mapper::dto2Bean)
		.map(promo -> {
			promo.setProductDescription(productRepository.findByProductId(promo.getProductId()).getDescription());
			return promo;
		})
		.collect(Collectors.toList());
	}
	
	@Override
	public Promotion findByProductId(String productId) {
		com.itv.test.web.repository.dto.Promotion promotion = promotionRepository.findByProductId(productId);
		if(promotion != null) {
			Promotion promoBean = mapper.dto2Bean(promotion);
			promoBean.setProductDescription(productRepository.findByProductId(promotion.getProductId()).getDescription());
			return promoBean;
		}
		else
			return null;
	}
	
}
