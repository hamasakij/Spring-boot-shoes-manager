package com.coltware.spring.dto;

import org.apache.groovy.parser.antlr4.util.StringUtils;

import com.coltware.spring.model.Product;

import lombok.Data;

@Data
public class NyuukoDto extends ZaikoDto {

	/**
	 * 商品
	 */
	private Product product;

	/**
	 * 入庫・出庫に表示する情報
	 */
	private StringBuffer productAllInfo;

	/**
	 * productAllInfoのsetter
	 * @param product
	 */
	public void setProductAllInfo(Product product) {
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
