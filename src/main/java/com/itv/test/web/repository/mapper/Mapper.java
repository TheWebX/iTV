package com.itv.test.web.repository.mapper;

public abstract class Mapper<S, R> {
	
	public abstract S dto2Bean(R dto);
	
	public abstract R bean2Dto(S dto);

}
