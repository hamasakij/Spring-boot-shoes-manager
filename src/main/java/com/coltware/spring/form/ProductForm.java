package com.coltware.spring.form;

import lombok.Data;

@Data

public class ProductForm {

	/**
	 * 商品ID
	 */
	private Long productId;
	
	/**
	 * カテゴリID
	 */
	private Long categoryId;
	
	/**
	 * 商品コード
	 */
	private Long productCode;
	
	/**
	 * メーカーID
	 */
	private Long makerId;
	
	/**
	 * 商品名
	 */
	private String productName;
	
	/**
	 * カラーID
	 */
	private Long colorId;
	
	/**
	 * サイズID
	 */
	private Long sizeId;
	
	/**
	 * 値段
	 */
	private Long price;
	
	/**
	 * 備考
	 */
	private String remarks;
	
	/**
     * サムネイル
     */
//    @Size(max=255)
    private String thumbnail;
}
