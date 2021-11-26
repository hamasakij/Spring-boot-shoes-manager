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

public class ZaikoDto {

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
	 * 在庫一覧に表示する情報(商品Code/商品名/color/size)
	 */
	private StringBuffer zaikoInfo;

	/**
	 * zaikoInfoのsetter
	 * @param product
	 */
	public void setZaikoInfo(Zaiko zaiko) {
		String slash = "/";
		StringBuffer zaikoInfo = new StringBuffer();

		if (product.getProductCode() == null) {
			zaikoInfo.append("未設定");
			zaikoInfo.append(slash);
		} else {
			zaikoInfo.append(product.getProductCode());
			zaikoInfo.append(slash);
		}
		if (StringUtils.isEmpty(product.getProductName())) {
			zaikoInfo.append("未設定");
			zaikoInfo.append(slash);
		} else {
			zaikoInfo.append(product.getProductName());
			zaikoInfo.append(slash);
		}
		if (product.getColor() == null) {
			zaikoInfo.append("未設定");
			zaikoInfo.append(slash);
		} else {
			product.getColor();
			zaikoInfo.append(product.getColor().getColorName());
			zaikoInfo.append(slash);
		}
		if (product.getSize() == null) {
			zaikoInfo.append("未設定");
		} else {
			zaikoInfo.append(product.getSize().getSize());
		}
		this.zaikoInfo = zaikoInfo;
	}

}
