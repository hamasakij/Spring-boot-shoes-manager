package com.coltware.spring.service.impl;

import static com.coltware.spring.specification.CategorySpecifications.*;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coltware.spring.exception.NotFoundException;
import com.coltware.spring.form.CategoryForm;
import com.coltware.spring.form.CategorySearchForm;
import com.coltware.spring.model.Category;
import com.coltware.spring.repository.CategoryRepository;
import com.coltware.spring.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	/**
	 * カテゴリ レポジトリ
	 */
	@Autowired
	private CategoryRepository categoryRepository;
	
	/**
	 * カテゴリのプルダウンメニュー取得
	 */
	public List<Category> getCategorys() {
		return categoryRepository.findAll();
	}

	/**
	 * カテゴリ一覧を取得
	 */
	@Override
	public List<Category> getCategorys(CategorySearchForm categorySearchForm) {

		Long categoryId = categorySearchForm.getCategoryId();
		String categoryName = categorySearchForm.getCategoryName();
		Boolean deleted = categorySearchForm.getDeleted();

		return categoryRepository.findAll(Specification.where(categoryIdContains(categoryId))
				.or(categoryNameContains(categoryName)).and(deletedContains(deleted)));
	}

	/**
	 * カテゴリの作成
	 */
	@Override
	public Category doInsert(CategoryForm categoryForm) {
		Category category = new Category();

		BeanUtils.copyProperties(categoryForm, category);

		return categoryRepository.save(category);
	}

	/**
	 * カテゴリの情報取得
	 */
	@Override
	public CategoryForm getCategory(Long categoryId) {
		if (categoryId == null) {
			throw new NotFoundException("指定したidがありません。");
		}
		Category category = categoryRepository.getById(categoryId);
		if (category == null) {
			throw new NotFoundException("対象のレコードが見つかりません。");
		}
		CategoryForm categoryForm = new CategoryForm();

		BeanUtils.copyProperties(category, categoryForm);
		return categoryForm;
	}

	/**
	 * カテゴリを更新する
	 */
	@Override
	public Category editCategory(CategoryForm categoryForm) {
		Category category = new Category();
		if (categoryForm == null) {
			throw new NotFoundException("対象のレコードが見つかりません");
		}
		BeanUtils.copyProperties(categoryForm, category);

		return categoryRepository.save(category);
	}

	/**
	 * Idをキーにカテゴリを削除
	 */
	@Override
	public Category deleteCategory(Long categoryId) {
		Category category = categoryRepository.getById(categoryId);

		category.setDeleted(true);
		return categoryRepository.save(category);
	}

}
