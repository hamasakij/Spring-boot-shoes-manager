package com.coltware.spring.form;

import lombok.Data;

@Data
public class ProductSearchForm {

	/**
	 * 商品名
	 */
	private String productName;

	/**
	 * 商品コード
	 */
	private Long productCode;

	/**
	 * 値段
	 */
	private Long price;
	/**
	 * 最低値段
	 */
	private Long minPrice;
	/**
	 * 最高値段
	 */
	private Long maxPrice;
	/**
	 * カテゴリID
	 */
	private Long categoryId;
	/**
	 * メーカーID
	 */
	private Long makerId;
	/**
	 * カラーID
	 */
	private Long colorId;
	/**
	 * サイズID
	 */
	private Long sizeId;
	
	/**
	 * 削除
	 */
	private Boolean deleted;
	
}
