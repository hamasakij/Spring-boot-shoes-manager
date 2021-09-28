package com.coltware.spring.service;

import java.util.List;

import com.coltware.spring.model.Product;

public interface ProductService {

	//商品の一覧
	public List<Product> getProducts(Product productId);
}
