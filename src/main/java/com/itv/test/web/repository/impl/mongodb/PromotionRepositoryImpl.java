package com.itv.test.web.repository.impl.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.itv.test.web.repository.PromotionRepository;
import com.itv.test.web.repository.dto.Promotion;

@Repository
@Profile("mongodb")
public class PromotionRepositoryImpl implements PromotionRepository {

	@Autowired
	private MongoOperations mongoOps;

	@Override
	public List<Promotion> findAll() {
		return mongoOps.findAll(Promotion.class);
	}

	@Override
	public Promotion findByProductId(String productId) {
		Query query = new Query(Criteria.where("productId").is(productId));
		return mongoOps.findOne(query, Promotion.class);
	}
	
}
