package com.itv.test.web.service;

import java.util.List;

import com.itv.test.beans.Product;
import com.itv.test.beans.Quote;

public interface CheckOutService  {

	/**
	 * Get quote of the Cart before to CheckOut
	 * 
	 * @param products
	 * @return
	 */
	public Quote getQuote(List<Product> products);
}
