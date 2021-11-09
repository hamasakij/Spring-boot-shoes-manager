package com.coltware.spring.dto;

import javax.persistence.Id;

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
	//在庫数
	private Long inventoryCount;

	/** join */
	// カテゴリ
	private Category category;
	// メーカー
	private Maker maker;
	// カラー
	private Color color;
	// サイズ
	private Size size;
	//在庫
	private Zaiko zaiko;
	//商品
	private Product product;

	// 商品ID/商品Code/商品名/color/size
	private StringBuffer productInfo;

	

	//入庫・出庫に表示する情報
	private StringBuffer productAllInfo;
	

	
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

	
	/**
	 * productAllInfoのsetter
	 * @param product
	 */
	public void setProductAllInfo(Product product) {
		String slash = "/";
		StringBuffer productAllInfo = new StringBuffer();

		if (getProductCode() == null) {
			productAllInfo.append("未設定です");
			productAllInfo.append(slash);
		} else {
			productAllInfo.append(product.getProductCode());
			productAllInfo.append(slash);
		}
		if (StringUtils.isEmpty(getProductName())) {
			productAllInfo.append("未設定です");
			productAllInfo.append(slash);
		} else {
			productAllInfo.append(product.getProductName());
			productAllInfo.append(slash);
		}
		if (product.getColor() == null) {
			productAllInfo.append("未設定です");
			productAllInfo.append(slash);
		} else {
			product.getColor();
			productAllInfo.append(product.getColor().getColorName());
			productAllInfo.append(slash);
		}
		if (product.getSize() == null) {
			productAllInfo.append("未設定です");
			productAllInfo.append(slash);
		} else {
			productAllInfo.append(product.getSize().getSize());
			productAllInfo.append(slash);
		}
		if (product.getCategory() == null) {
			productAllInfo.append("未設定です");
			productAllInfo.append(slash);
		} else {
			productAllInfo.append(product.getCategory().getCategoryName());
			productAllInfo.append(slash);
		}
		if (product.getMaker() == null) {
			productAllInfo.append("未設定です");
			productAllInfo.append(slash);
		} else {
			productAllInfo.append(product.getMaker().getMakerName());
			productAllInfo.append(slash);
		}
		if (product.getPrice() == null) {
			productAllInfo.append("未設定です");
		} else {
			productAllInfo.append(product.getPrice());
		}
		this.productAllInfo = productAllInfo;
	}
	
}
