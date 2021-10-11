package com.coltware.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.coltware.spring.model.Category;
import com.coltware.spring.repository.CategoryRepository;
import com.coltware.spring.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	/**
	 * カテゴリ一覧を取得
	 */
	@Override
	public List<Category> getCategorys(Category categoryId) {
		List<Category> list = categoryRepository.findAll();
		return list;
	}

}
