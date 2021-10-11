package com.coltware.spring.dto;

import javax.persistence.Id;

import com.coltware.spring.model.Category;
import com.coltware.spring.model.Maker;

import lombok.Data;

@Data

public class ProductDto {

	@Id

	// 商品ID
	private Long productId;
	// カテゴリID
	private Long categoryId;
	// 商品コード
	private Long productCode;
	// メーカーID
	private Long makerId;
	// 商品名
	private String productName;
	// カラーID
	private Long colorId;
	// サイズID
	private Long sizeId;
	// 値段
	private Long price;
	// 備考
	private String remarks;

	/** join */
	// カテゴリ
	private Category category;
	// メーカー
	private Maker maker;

	
	//商品ID/商品Code/商品名/color/size
	private String productInfo;
}
