package com.coltware.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coltware.spring.dto.ProductDto;
import com.coltware.spring.form.ProductForm;
import com.coltware.spring.model.Product;
import com.coltware.spring.repository.ProductRepository;
import com.coltware.spring.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;


	/**商品一覧取得
	 * IDや名前などをリストにし、1つにまとめる
	 * カテゴリなどID表示のものを名前で表示する
	 */
	@Override
	public List<ProductDto> getProducts(Product productId){
		List<Product> list = productRepository.findAll();
		List<ProductDto> resultList = new ArrayList<ProductDto>();
		for(Product product:list) {
			String name = product.getProductId() + "/" + product.getProductCode() + "/" + product.getProductName() + "/" + product.getColor().getColor() + "/" + product.getSize().getSize();
			ProductDto dto = new ProductDto();
			BeanUtils.copyProperties(product, dto);
			dto.setProductInfo(name);
			resultList.add(dto);
		}
		
		return resultList;
	}

	/**
	 * 商品の作成
	 */
	@Override
	public Product doInsert(ProductForm productForm) {
		Product product = new Product();
		BeanUtils.copyProperties(productForm, product);
		return productRepository.save(product);		
	}
	/**
	 * idをキーに商品を削除
	 */
	@Override
	public Product deleteProduct(Long productId) {
     
		Product product = productRepository.getById(productId);
		product.setDeleted(true);
		product.addDeletedDate();
		return productRepository.save(product);
	}

	

}
