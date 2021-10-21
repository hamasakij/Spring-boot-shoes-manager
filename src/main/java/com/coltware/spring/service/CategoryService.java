package com.coltware.spring.service;

import java.util.List;

import com.coltware.spring.form.CategoryForm;
import com.coltware.spring.form.CategorySearchForm;
import com.coltware.spring.model.Category;

public interface CategoryService {

	/**
	 * カテゴリのプルダウンメニュー取得
	 * @return
	 */
	public List<Category> getCategorys();
	/**
	 * カテゴリ一覧
	 * @param categoryId
	 * @return
	 */
	public List<Category> getCategorys(CategorySearchForm categorySearchForm);
	
	/**
	 * カテゴリの作成
	 */
	public Category doInsert(CategoryForm categoryForm);
	
	/**
	 * Idをもとにカテゴリの情報を取得
	 * @param caetgoryId
	 * @return
	 */
	public CategoryForm getCategory(Long caetgoryId);

	/**
	 * カテゴリの情報を更新
	 * @param categoryForm
	 * @return
	 */
	public Category editCategory(CategoryForm categoryForm);
	
	/**
	 * Idをもとにカテゴリを削除
	 * @param categoryId
	 * @return
	 */
	public Category deleteCategory(Long categoryId);
}
