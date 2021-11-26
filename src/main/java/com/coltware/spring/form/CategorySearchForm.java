package com.coltware.spring.form;

import lombok.Data;

@Data
public class CategorySearchForm {

	/**
	 * カテゴリid
	 */
	private Long categoryId;
	
	/**
	 * カテゴリ名
	 */
	private String categoryName;

	/**
	 * 削除
	 */
	private Boolean deleted;
}
