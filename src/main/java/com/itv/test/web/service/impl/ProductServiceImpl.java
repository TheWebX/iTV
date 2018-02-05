package com.itv.test.web.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itv.test.beans.Product;
import com.itv.test.web.repository.ProductRepository;
import com.itv.test.web.repository.mapper.Mapper;
import com.itv.test.web.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private Mapper<Product, com.itv.test.web.repository.dto.Product> productMapper;

	@Autowired
	private ProductRepository productRepository;

	public Mapper<Product, com.itv.test.web.repository.dto.Product> getProductMapper() {
		return productMapper;
	}

	public void setProductMapper(Mapper<Product, com.itv.test.web.repository.dto.Product> productMapper) {
		this.productMapper = productMapper;
	}

	public ProductRepository getProductRepository() {
		return productRepository;
	}

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll().stream().map(productMapper::dto2Bean).collect(Collectors.toList());
	}
	
	@Override
	public Product findByProductId(String productId) {
		com.itv.test.web.repository.dto.Product res = productRepository.findByProductId(productId);
		if(res != null)
			return productMapper.dto2Bean(res);
		else
			return null;
	}

}
