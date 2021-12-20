package com.coltware.spring.dto;

import org.apache.groovy.parser.antlr4.util.StringUtils;

import com.coltware.spring.model.Category;
import com.coltware.spring.model.Color;
import com.coltware.spring.model.Maker;
import com.coltware.spring.model.Product;
import com.coltware.spring.model.Size;

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
	 * カテゴリ名
	 */
	private String categoryName;

	/**
	 * メーカー
	 */
	private Maker maker;

	/**
	 * メーカー名
	 */
	private String makerName;

	/**
	 * カラー
	 */
	private Color color;

	/**
	 * サイズ
	 */
	private Size size;

	/**
	 * 商品ID/商品Code/商品名/color/sizeの形式で表示
	 */
	private StringBuffer productInfo;

	/**
	 * カテゴリ名の
	 * @param product
	 */
	public void setCategoryName(Product product) {
		String name = null;

		if (product.getCategory() == null) {
			name = "未設定";
		} else {
			name = product.getCategory().getCategoryName();
		}
		 this.categoryName = name;
	}

	
	public void setMakerName(Product product) {
		String name = null;

		if (product.getMaker() == null) {
			name = "未設定";
		} else {
			name = product.getMaker().getMakerName();
		}
		 this.makerName = name;
	}
	
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
