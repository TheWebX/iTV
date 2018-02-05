package com.itv.test.beans;

import java.util.ArrayList;
import java.util.List;

public class Quote {

	private List<Product> products = new ArrayList<>();
	private List<Promotion> appliedPromotions = new ArrayList<>();
	private Double totalPrice = 0.0D;
	private Double totalDiscountAmount = 0.0D;
	private Double totalPreDiscountAmount = 0.0D;

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

	public Double getTotalPreDiscountAmount() {
		return totalPreDiscountAmount;
	}

	public void setTotalPreDiscountAmount(Double totalPreDiscountAmount) {
		this.totalPreDiscountAmount = totalPreDiscountAmount;
	}

}
