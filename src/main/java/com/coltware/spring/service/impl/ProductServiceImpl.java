package com.coltware.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coltware.spring.model.Product;
import com.coltware.spring.repository.ProductRepository;
import com.coltware.spring.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getProducts(Product productId) {
		return productRepository.findAll();
	}

}
