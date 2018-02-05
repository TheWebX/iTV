package com.itv.test.web.repository.dto;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "com.itv.test.web.repository.dto.Promotion")
@CompoundIndexes({ @CompoundIndex(name = "productId_1", def = "{'productId': 1}") })
public class Promotion {

    @Indexed(direction = IndexDirection.ASCENDING)
	private String productId;
	private Integer quantity;
	private Double price;
	
    @PersistenceConstructor
	public Promotion(String productId, Integer quantity, Double price) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
