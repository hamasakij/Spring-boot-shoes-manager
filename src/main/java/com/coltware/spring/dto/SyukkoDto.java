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
public class SyukkoDto {

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
	 * 入庫・出庫に表示する情報(商品Code/商品名/color/size/カテゴリ名/値段)
	 */
	private StringBuffer productAllInfo;

	/**
	 * productAllInfoのsetter
	 * @param zaiko
	 */
	public void setProductAllInfo(Zaiko zaiko) {
		String slash = "/";
		StringBuffer productAllInfo = new StringBuffer();

		if (product.getProductCode() == null) {
			productAllInfo.append("未設定");
			productAllInfo.append(slash);
		} else {
			productAllInfo.append(product.getProductCode());
			productAllInfo.append(slash);
		}
		if (StringUtils.isEmpty(product.getProductName())) {
			productAllInfo.append("未設定");
			productAllInfo.append(slash);
		} else {
			productAllInfo.append(product.getProductName());
			productAllInfo.append(slash);
		}
		if (product.getColor() == null) {
			productAllInfo.append("未設定");
			productAllInfo.append(slash);
		} else {
			product.getColor();
			productAllInfo.append(product.getColor().getColorName());
			productAllInfo.append(slash);
		}
		if (product.getSize() == null) {
			productAllInfo.append("未設定");
			productAllInfo.append(slash);
		} else {
			productAllInfo.append(product.getSize().getSize());
			productAllInfo.append(slash);
		}
		if (product.getCategory() == null) {
			productAllInfo.append("未設定");
			productAllInfo.append(slash);
		} else {
			productAllInfo.append(product.getCategory().getCategoryName());
			productAllInfo.append(slash);
		}
		if (product.getMaker() == null) {
			productAllInfo.append("未設定");
			productAllInfo.append(slash);
		} else {
			productAllInfo.append(product.getMaker().getMakerName());
			productAllInfo.append(slash);
		}
		if (product.getPrice() == null) {
			productAllInfo.append("未設定");
		} else {
			productAllInfo.append(product.getPrice());
		}
		this.productAllInfo = productAllInfo;
	}

}
