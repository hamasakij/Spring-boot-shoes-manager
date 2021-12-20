package com.coltware.spring.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.coltware.spring.form.valid.ValidGroup1;

import lombok.Data;

@Data
public class ProductSearchForm {

	
	/**
	 * 商品名
	 */
	@Size(max = 20,groups = ValidGroup1.class)
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
	@Min(value = 0,groups = ValidGroup1.class)
	private Long minPrice;
	
	/**
	 * 最高値段
	 */
	private Long maxPrice;
	
	/**
	 * カテゴリID
	 */
	private Long categoryId;
	
	private String categoryName;
	
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
