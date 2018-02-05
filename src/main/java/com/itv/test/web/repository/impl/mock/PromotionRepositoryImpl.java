package com.itv.test.web.repository.impl.mock;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.itv.test.web.repository.PromotionRepository;
import com.itv.test.web.repository.dto.Promotion;

@Repository
@Profile("mock")
public class PromotionRepositoryImpl implements PromotionRepository {

	@Override
	public List<Promotion> findAll() {
		return Arrays.asList(new Promotion("A", 3, 130.0), new Promotion("B", 2, 45.0));
	}

	@Override
	public Promotion findByProductId(String productId) {
		if("A".equals(productId)) {
			return new Promotion("A", 3, 130.0);
		}else if("B".equals(productId)) {
			return new Promotion("B", 2, 45.0);
		}else {
			return null;
		}
	}
	
}
