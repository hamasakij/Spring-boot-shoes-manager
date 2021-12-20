package com.coltware.spring.service;

import java.util.List;

import com.coltware.spring.dto.ProductDto;
import com.coltware.spring.form.ProductForm;
import com.coltware.spring.form.ProductSearchForm;
import com.coltware.spring.model.Product;

/**
 * 商品 サービス
 */
public interface ProductService {

	
	/**
	 * 商品の一覧条件付きで表示
	 * 
	 * @param productSearchForm
	 * @return
	 */
	public List<ProductDto> getProducts(ProductSearchForm productSearchForm);

	/**
	 * 商品の作成
	 * 
	 * @param productForm
	 * @return
	 */
	public Product doInsert(ProductForm productForm);

	/**
	 * idをキーに商品の詳細情報を取得する
	 * 
	 * @param productId
	 * @return
	 */
	public ProductForm getDetail(Long productId);

	/**
	 * 商品の詳細情報を編集する
	 * 
	 * @param productorm
	 * @return
	 */
	public Product detailUpdate(ProductForm productForm);

	/**
	 * 商品を削除(論理削除)
	 * 
	 * @param productId
	 * @return
	 */
	public Product deleteProduct(Long productId);
}
