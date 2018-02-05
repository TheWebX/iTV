package com.itv.test.web.repository.impl.mock;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.itv.test.web.repository.ProductRepository;
import com.itv.test.web.repository.dto.Product;

@Repository
@Profile("mock")
public class ProductRepositoryImpl implements ProductRepository {

	@Override
	public List<Product> findAll() {
		return Arrays.asList(new Product("A", 50.0, "Product A"), new Product("B", 30.0, "Product B"), new Product("C", 20.0, "Product C"), new Product("D", 15.0, "Product D"));
	}
	
	@Override
	public Product findByProductId(String productId) {
		if("A".equals(productId)) {
			return new Product("A", 50.0, "Product A");
		}else if("B".equals(productId)) {
			return new Product("B", 30.0, "Product B");
		}else if("C".equals(productId)) {
			return new Product("C", 20.0, "Product C");
		}else if("D".equals(productId)) {
			return new Product("D", 15.0, "Product D");
		}else {
			return null;
		}
	}

}
