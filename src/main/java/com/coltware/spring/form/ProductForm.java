package com.coltware.spring.form;

import lombok.Data;

@Data

public class ProductForm {

	//商品ID
	private Integer productId;
	
	//カテゴリID
	private Integer categoryId;
	
	//商品コード
	private Integer productCode;
	
	//メーカーID
	private Integer makerId;
	
	//商品名
	private String productName;
	
	//カラーID
	private Integer colorId;
	
	//サイズID
	private Integer sizeId;
	
	//値段
	private Integer price;
	
	//備考
	private String remarks;
}
