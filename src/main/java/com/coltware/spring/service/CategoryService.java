package com.coltware.spring.service;

import java.util.List;

import com.coltware.spring.model.Category;

public interface CategoryService {

	/**
	 * カテゴリ一覧
	 * @param categoryId
	 * @return
	 */
	public List<Category> getCategorys(Category categoryId);
}
