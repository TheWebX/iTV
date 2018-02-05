package com.itv.test.web.repository.impl.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.itv.test.web.repository.ProductRepository;
import com.itv.test.web.repository.dto.Product;

@Repository
@Profile("mongodb")
public class ProductRepositoryImpl implements ProductRepository {

	@Autowired
	private MongoOperations mongoOps;

	@Override
	public List<Product> findAll() {
		return mongoOps.findAll(Product.class);
	}
	
	@Override
	public Product findByProductId(String productId) {
		Query query = new Query(Criteria.where("productId").is(productId));
		return mongoOps.find(query, Product.class).iterator().next();
	}

}
