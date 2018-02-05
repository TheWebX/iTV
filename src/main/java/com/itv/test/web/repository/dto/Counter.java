package com.itv.test.web.repository.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "com.itv.test.web.repository.dto.OrderCounter")
@CompoundIndexes({ @CompoundIndex(name = "orderCounterId_1", def = "{'orderCounterId': 1}") })
public class Counter {

	@Id
	private String orderCounterId;
	private Double value;

	public String getOrderCounterId() {
		return orderCounterId;
	}

	public void setOrderCounterId(String orderCounterId) {
		this.orderCounterId = orderCounterId;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
