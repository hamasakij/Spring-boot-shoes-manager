package com.coltware.spring.service;

import java.util.List;

import com.coltware.spring.dto.ProductDto;
import com.coltware.spring.form.ProductForm;
import com.coltware.spring.model.Product;

public interface ProductService {

	/**
	 * 商品の一覧
	 * @param productId
	 * @return
	 */
	public List<ProductDto> getProducts(Product productId);

	/**
	 * 商品の作成
	 * 
	 * @param productForm
	 * @return
	 */
	public Product doInsert(ProductForm productForm);

	/**
	 * 商品を削除(論理削除)
	 * 
	 * @param productId
	 * @return
	 */
	public Product deleteProduct(Long productId);
}
