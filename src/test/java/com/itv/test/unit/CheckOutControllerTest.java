package com.itv.test.unit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.itv.test.beans.Product;
import com.itv.test.beans.Promotion;
import com.itv.test.beans.Quote;
import com.itv.test.web.controller.CheckOutRestController;
import com.itv.test.web.service.CheckOutService;
import com.itv.test.web.service.ProductService;
import com.itv.test.web.service.PromotionService;
import com.itv.test.web.service.impl.CheckOutServiceImpl;

import static org.junit.Assert.assertEquals;

public class CheckOutControllerTest {

	private CheckOutService checkOutService;
	private ProductService productService;
	
	@Before
	public void initialize() {
		Promotion promoA = new Promotion();
		promoA.setPrice(8.00);
		promoA.setProductId("A");
		promoA.setQuantity(3);
		Promotion promoB = new Promotion();
		promoB.setPrice(3.00);
		promoB.setProductId("B");
		promoB.setQuantity(2);
		PromotionService promotionService = mock(PromotionService.class);
		when(promotionService.findAll()).thenReturn(Arrays.asList(promoA, promoB));
		when(promotionService.findByProductId("A")).thenReturn(promoA);
		when(promotionService.findByProductId("B")).thenReturn(promoB);
		CheckOutServiceImpl checkOutServiceImpl = new CheckOutServiceImpl();
		checkOutServiceImpl.setPromotionService(promotionService);
		checkOutService = checkOutServiceImpl;
		productService = mock(ProductService.class);
		Product productA = new Product();
		productA.setProductId("A");
		productA.setPrice(4.00);
		productA.setDescription("Product A");
		Product productB = new Product();
		productB.setProductId("B");
		productB.setPrice(3.00);
		productB.setDescription("Product A");
		Product productC = new Product();
		productC.setProductId("C");
		productC.setPrice(2.00);
		productC.setDescription("Product C");
		Product productD = new Product();
		productD.setProductId("D");
		productD.setPrice(1.00);
		productD.setDescription("Product D");
		when(productService.findAll()).thenReturn(Arrays.asList(productA, productB, productC, productD));
		when(productService.findByProductId("A")).thenReturn(productA);
		when(productService.findByProductId("B")).thenReturn(productB);
		when(productService.findByProductId("C")).thenReturn(productC);
		when(productService.findByProductId("D")).thenReturn(productD);
	}

	@Test
	public void testCheckOutController() {
		CheckOutRestController checkOutRestController = new CheckOutRestController();
		checkOutRestController.setCheckOutService(checkOutService);
		checkOutRestController.setProductService(productService);
		Quote quote = checkOutRestController.getQuote(Arrays.asList("A", "A", "A", "B", "B", "B", "B", "C", "D"));
		assertEquals(9, quote.getProducts().size());
		assertEquals(3, quote.getAppliedPromotions().size());
		assertEquals(Double.valueOf(17.00), quote.getTotalPrice());
		assertEquals(Double.valueOf(10.00), quote.getTotalDiscountAmount());
	}
	
}
