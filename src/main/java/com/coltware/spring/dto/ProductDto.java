package com.coltware.spring.dto;

import javax.persistence.Id;

import org.apache.groovy.parser.antlr4.util.StringUtils;

import com.coltware.spring.model.Category;
import com.coltware.spring.model.Color;
import com.coltware.spring.model.Maker;
import com.coltware.spring.model.Product;
import com.coltware.spring.model.Size;

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
	private Long minPrice;
	private Long maxPrice;
	// 備考
	private String remarks;

	/** join */
	// カテゴリ
	private Category category;
	// メーカー
	private Maker maker;
	// カラー
	private Color color;
	// サイズ
	private Size size;

	// 商品ID/商品Code/商品名/color/size
	private StringBuffer productInfo;



	/**
	 * productInfoのsetter
	 * 
	 * @param product
	 */
	public void setProductInfo(Product product) {
		String slash = "/";
		StringBuffer productInfo = new StringBuffer();

		productInfo.append(product.getProductId());
		productInfo.append(slash);

		if (getProductCode() == null) {
			productInfo.append("未設定です");
			productInfo.append(slash);
		} else {
			productInfo.append(product.getProductCode());
			productInfo.append(slash);
		}
		if (StringUtils.isEmpty(getProductName())) {
			productInfo.append("未設定です");
			productInfo.append(slash);
		} else {
			productInfo.append(product.getProductName());
			productInfo.append(slash);
		}
		if (product.getColor() == null) {
			productInfo.append("未設定です");
			productInfo.append(slash);
		} else {
			product.getColor();
			productInfo.append(product.getColor().getColorName());
			productInfo.append(slash);
		}
		if (product.getSize() == null) {
			productInfo.append("未設定です");
		} else {
			productInfo.append(product.getSize().getSize());
		}
		this.productInfo = productInfo;
	}

}
