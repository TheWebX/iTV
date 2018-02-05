package com.itv.test.web.repository.mapper;

import org.springframework.stereotype.Component;

import com.itv.test.beans.Promotion;

@Component
public class PromotionMapper extends Mapper<com.itv.test.beans.Promotion, com.itv.test.web.repository.dto.Promotion> {

	@Override
	public Promotion dto2Bean(com.itv.test.web.repository.dto.Promotion dto) {
		Promotion product = new Promotion();
		product.setProductId(dto.getProductId());
		product.setQuantity(dto.getQuantity());
		product.setPrice(dto.getPrice());
		return product;
	}

	@Override
	public com.itv.test.web.repository.dto.Promotion bean2Dto(Promotion bean) {
		return new com.itv.test.web.repository.dto.Promotion(bean.getProductId(), bean.getQuantity(), bean.getPrice());
	}

}
