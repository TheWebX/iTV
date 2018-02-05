package com.itv.test.web.repository.dto;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "com.itv.test.web.repository.dto.Product")
@CompoundIndexes({ @CompoundIndex(name = "productId_1", def = "{'productId': 1}") })
public class Product {

    @Indexed(direction = IndexDirection.ASCENDING)
	private String productId;
	private Double price;
	private String description;

    @PersistenceConstructor
    public Product(String productId, Double price, String description) {
		super();
		this.productId = productId;
		this.price = price;
		this.description = description;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
