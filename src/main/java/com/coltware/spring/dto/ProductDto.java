package com.coltware.spring.dto;

import org.apache.groovy.parser.antlr4.util.StringUtils;

import com.coltware.spring.model.Category;
import com.coltware.spring.model.Color;
import com.coltware.spring.model.Maker;
import com.coltware.spring.model.Product;
import com.coltware.spring.model.Size;
import com.coltware.spring.model.Zaiko;

import lombok.Data;

@Data

public class ProductDto {

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
	 * 最低金額
	 */
	private Long minPrice;

	/**
	 * 最高金額
	 */
	private Long maxPrice;

	/**
	 * 備考
	 */
	private String remarks;

	/**
	 * 在庫数
	 */
	private Long inventoryCount;

	/**
	 * カテゴリ
	 */
	private Category category;

	/**
	 * メーカー
	 */
	private Maker maker;

	/**
	 * カラー
	 */
	private Color color;

	/**
	 * サイズ
	 */
	private Size size;

	/**
	 * 在庫
	 */
	private Zaiko zaiko;

	/**
	 * 商品
	 */
	private Product product;

	/**
	 * 商品ID/商品Code/商品名/color/sizeの形式で表示
	 */
	private StringBuffer productInfo;

	/**
	 * productInfoのsetter
	 * @param product
	 */
	public void setProductInfo(Product product) {
		String slash = "/";
		StringBuffer productInfo = new StringBuffer();

		productInfo.append(product.getProductId());
		productInfo.append(slash);

		if (getProductCode() == null) {
			productInfo.append("未設定");
			productInfo.append(slash);
		} else {
			productInfo.append(product.getProductCode());
			productInfo.append(slash);
		}
		if (StringUtils.isEmpty(getProductName())) {
			productInfo.append("未設定");
			productInfo.append(slash);
		} else {
			productInfo.append(product.getProductName());
			productInfo.append(slash);
		}
		if (product.getColor() == null) {
			productInfo.append("未設定");
			productInfo.append(slash);
		} else {
			product.getColor();
			productInfo.append(product.getColor().getColorName());
			productInfo.append(slash);
		}
		if (product.getSize() == null) {
			productInfo.append("未設定");
		} else {
			productInfo.append(product.getSize().getSize());
		}
		this.productInfo = productInfo;
	}

}
