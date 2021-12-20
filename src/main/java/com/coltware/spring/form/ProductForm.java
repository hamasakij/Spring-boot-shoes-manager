package com.coltware.spring.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.coltware.spring.form.valid.ValidGroup1;
import com.coltware.spring.form.valid.ValidGroup2;

import lombok.Data;

@Data

public class ProductForm {

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
	@NotNull(groups = ValidGroup1.class)
	private Long productCode;

	/**
	 * メーカーID
	 */
	private Long makerId;

	/**
	 * 商品名
	 */
	@NotBlank(groups = ValidGroup1.class)
	@Size(min = 2, max = 20, groups = ValidGroup2.class)
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
	@NotNull(groups = ValidGroup1.class)
	@Min(value = 0,groups = ValidGroup2.class)
	private Long price;

	/**
	 * 備考
	 */
	@Size(max = 200)
	private String remarks;

	/**
	 * サムネイル
	 */
//    @Size(max=255)
	private String thumbnail;
}
