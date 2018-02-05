package com.itv.test.web.repository.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "com.itv.test.web.repository.dto.Order")
@CompoundIndexes({ @CompoundIndex(name = "orderId_1", def = "{'orderId': 1}") })
public class Order {

	@Id
	private String orderId;
	private List<Product> products = new ArrayList<>();
	private List<Promotion> appliedPromotions = new ArrayList<>();
	private Double totalPrice = 0.00;
	private Double totalDiscountAmount = 0.00;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Promotion> getAppliedPromotions() {
		return appliedPromotions;
	}

	public void setAppliedPromotions(List<Promotion> appliedPromotions) {
		this.appliedPromotions = appliedPromotions;
	}

	public Double getTotalDiscountAmount() {
		return totalDiscountAmount;
	}

	public void setTotalDiscountAmount(Double totalDiscountAmount) {
		this.totalDiscountAmount = totalDiscountAmount;
	}

}
