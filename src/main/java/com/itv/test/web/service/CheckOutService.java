package com.itv.test.web.service;

import java.util.List;

import com.itv.test.beans.Product;
import com.itv.test.beans.Quote;

public interface CheckOutService  {

	public Quote getQuote(List<Product> products);
}
