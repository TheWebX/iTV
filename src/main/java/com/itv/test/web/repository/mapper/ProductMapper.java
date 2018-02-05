package com.itv.test.web.repository.mapper;

import org.springframework.stereotype.Component;

import com.itv.test.beans.Product;

@Component
public class ProductMapper extends Mapper<com.itv.test.beans.Product, com.itv.test.web.repository.dto.Product> {

	@Override
	public Product dto2Bean(com.itv.test.web.repository.dto.Product dto) {
		Product product = new Product();
		product.setProductId(dto.getProductId());
		product.setPrice(dto.getPrice());
		product.setDescription(dto.getDescription());
		return product;
	}

	@Override
	public com.itv.test.web.repository.dto.Product bean2Dto(Product bean) {
		return new com.itv.test.web.repository.dto.Product(bean.getProductId(), bean.getPrice(), bean.getDescription());
	}

}
