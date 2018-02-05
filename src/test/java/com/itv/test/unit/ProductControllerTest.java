package com.itv.test.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.itv.test.web.controller.ProductRestController;
import com.itv.test.web.repository.ProductRepository;
import com.itv.test.web.repository.dto.Product;
import com.itv.test.web.repository.mapper.ProductMapper;
import com.itv.test.web.service.ProductService;
import com.itv.test.web.service.impl.ProductServiceImpl;

public class ProductControllerTest {
	
	private ProductService productService;

	@Before
	public void initialize() {
		Product productA = new Product("A", 4.00, "Product A");
		Product productB = new Product("B", 3.00, "Product A");
		Product productC = new Product("C", 2.00, "Product C");
		Product productD = new Product("D", 1.00, "Product D");
		ProductRepository productRepository = mock(ProductRepository.class);
		when(productRepository.findAll()).thenReturn(Arrays.asList(productA, productB, productC, productD));
		when(productRepository.findByProductId("A")).thenReturn(productA);
		when(productRepository.findByProductId("B")).thenReturn(productB);
		when(productRepository.findByProductId("C")).thenReturn(productC);
		when(productRepository.findByProductId("D")).thenReturn(productD);
		ProductServiceImpl productServiceImpl = new ProductServiceImpl();
		productServiceImpl.setProductRepository(productRepository);
		productServiceImpl.setProductMapper(new ProductMapper());
		productService = productServiceImpl;
	}

	@Test
	public void testProductOutController() {
		ProductRestController productRestController = new ProductRestController();
		productRestController.setProductService(productService);
		assertEquals(4, productRestController.findAll().size());
		com.itv.test.beans.Product productB = new com.itv.test.beans.Product();
		productB.setProductId("B");
		productB.setPrice(3.00);
		productB.setDescription("Product A");
		com.itv.test.beans.Product toCompareProduct = productRestController.findByProductId("B");
		assertEquals(productB.getProductId(), toCompareProduct.getProductId());
		assertEquals(productB.getPrice(), toCompareProduct.getPrice());
		assertEquals(productB.getDescription(), toCompareProduct.getDescription());
	}
	
}
