package com.itv.test.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.itv.test.web.controller.PromotionRestController;
import com.itv.test.web.repository.ProductRepository;
import com.itv.test.web.repository.dto.Product;
import com.itv.test.web.repository.dto.Promotion;
import com.itv.test.web.repository.impl.mock.PromotionRepositoryImpl;
import com.itv.test.web.repository.mapper.PromotionMapper;
import com.itv.test.web.service.PromotionService;
import com.itv.test.web.service.impl.PromotionServiceImpl;

public class PromotionControllerTest {
	
	private PromotionService promotionService;

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
		Promotion promoA = new Promotion("A", 3, 8.00);
		Promotion promoB = new Promotion("B", 2, 3.0);
		PromotionRepositoryImpl promotionRepositoryImpl = mock(PromotionRepositoryImpl.class);
		when(promotionRepositoryImpl.findAll()).thenReturn(Arrays.asList(promoA, promoB));
		when(promotionRepositoryImpl.findByProductId("A")).thenReturn(promoA);
		when(promotionRepositoryImpl.findByProductId("B")).thenReturn(promoB);
		PromotionServiceImpl promotionServiceImpl = new PromotionServiceImpl();
		promotionServiceImpl.setPromotionRepository(promotionRepositoryImpl);
		promotionServiceImpl.setProductRepository(productRepository);
		promotionServiceImpl.setMapper(new PromotionMapper());
		promotionService = promotionServiceImpl;
	}

	@Test
	public void testProductOutController() {
		PromotionRestController promotionRestController = new PromotionRestController();
		promotionRestController.setPromotionService(promotionService);
		assertEquals(2, promotionRestController.findAll().size());
		com.itv.test.beans.Promotion promoB = new com.itv.test.beans.Promotion();
		promoB.setProductId("B");
		promoB.setQuantity(2);
		promoB.setPrice(3.00);
		com.itv.test.beans.Promotion toComparePromo = promotionRestController.findByProductId("B");
		assertEquals(promoB.getProductId(), toComparePromo.getProductId());
		assertEquals(promoB.getQuantity(), toComparePromo.getQuantity());
		assertEquals(promoB.getPrice(), toComparePromo.getPrice());
	}
	
}
