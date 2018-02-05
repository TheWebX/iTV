package com.itv.test.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itv.test.beans.Product;
import com.itv.test.beans.Promotion;
import com.itv.test.beans.Quote;
import com.itv.test.web.service.CheckOutService;
import com.itv.test.web.service.PromotionService;

@Service
public class CheckOutServiceImpl implements CheckOutService {

	@Autowired
	private PromotionService promotionService;
	
	public PromotionService getPromotionService() {
		return promotionService;
	}

	public void setPromotionService(PromotionService promotionService) {
		this.promotionService = promotionService;
	}

	@Override
	public Quote getQuote(List<Product> products) {
		Map<String, Promotion> cartPromotions = new HashMap<>();
		Map<String, Integer> productByHits = new HashMap<>();
		Quote quote = new Quote();
		for(Product product : products) {
			Integer hits = productByHits.get(product.getProductId());
			if(hits != null) {
				hits++;
			}else{
				hits=1;
			}
			productByHits.put(product.getProductId(), hits);
			Promotion promotion = cartPromotions.get(product.getProductId());
			if(promotion == null) {
				promotion = promotionService.findByProductId(product.getProductId());
				if(promotion != null) {
					cartPromotions.put(product.getProductId(), promotion);
				}
			}
			quote.setTotalPrice(quote.getTotalPrice() + product.getPrice());
			quote.setTotalPreDiscountAmount(quote.getTotalPreDiscountAmount() + product.getPrice());
			quote.getProducts().add(product);
			if(promotion != null) {
				if(hits == promotion.getQuantity()) {
					Double priceWithoutDiscount = product.getPrice() * promotion.getQuantity();
					quote.setTotalPrice(quote.getTotalPrice() - priceWithoutDiscount);
					quote.setTotalPrice(quote.getTotalPrice() + promotion.getPrice());
					quote.setTotalDiscountAmount(quote.getTotalDiscountAmount() + (priceWithoutDiscount - promotion.getPrice()));
					quote.getAppliedPromotions().add(promotion);
					productByHits.put(product.getProductId(), 0);
				}
			}
		}
		return quote;
	}
	
}
