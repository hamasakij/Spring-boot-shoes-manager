package com.coltware.spring.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.coltware.spring.form.CategoryForm;
import com.coltware.spring.form.CategorySearchForm;
import com.coltware.spring.form.valid.GroupOrder;
import com.coltware.spring.model.Category;
import com.coltware.spring.response.CategoryJsonResponse;
import com.coltware.spring.service.CategoryService;

@Controller
@RequestMapping("/master")
public class CategoryController {

	/**
	 * カテゴリのサービスクラス
	 */
	@Autowired
	private CategoryService categoryService;

	/**
	 * カテゴリ一覧の取得
	 * @param categorySearchForm
	 * @param mav
	 * @return
	 */
	@GetMapping("/category")
	public ModelAndView index(@ModelAttribute CategorySearchForm categorySearchForm, ModelAndView mav) {
		// カテゴリの一覧取得
		List<Category> categoryList = categoryService.getCategorys(categorySearchForm);
		List<Category> category = categoryService.getCategorys();
		// ModelAndViewに登録
		mav.addObject("categorys", categoryList);
		mav.addObject("category",category);

		
		mav.addObject("categorySearchForm", categorySearchForm);
		mav.addObject("categoryForm", new CategoryForm());
		// 表示するビュー
		mav.setViewName("master/category/index");
		return mav;
	}

	/**
	 * カテゴリの新規作成
	 * @param categoryForm
	 * @param errorResult
	 * @return
	 */
	@PostMapping("/category/create")
	@ResponseBody
	public CategoryJsonResponse create(@Validated(GroupOrder.class) @RequestBody CategoryForm categoryForm,
			BindingResult errorResult) {

		CategoryJsonResponse categoryJsonResponse = new CategoryJsonResponse();
		categoryJsonResponse.setCategoryName(categoryForm.getCategoryName());

		if (errorResult.hasErrors()) {
			Map<String, String> errors = errorResult.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

			categoryJsonResponse.setSuccess(false);
			categoryJsonResponse.setMessage("入力エラーがあります");
			categoryJsonResponse.setErrors(errors);

			return categoryJsonResponse;
		}
		Category category = categoryService.doInsert(categoryForm);

		if (category == null) {
			throw new RuntimeException("新規作成に失敗しました");
		} else {
			categoryJsonResponse.setSuccess(true);
			categoryJsonResponse.setMessage("新規作成しました");
		}
		return categoryJsonResponse;
	}

	/**
	 * カテゴリの更新
	 * @param categoryId
	 * @param categoryForm
	 * @param errorResult
	 * @return
	 */
	@PostMapping("/category/{categoryId}/edit")
	@ResponseBody
	public CategoryJsonResponse edit(@PathVariable Long categoryId,
			@Validated(GroupOrder.class) @RequestBody CategoryForm categoryForm, BindingResult errorResult) {

		CategoryJsonResponse categoryJsonResponse = new CategoryJsonResponse();
		categoryJsonResponse.setCategoryId(categoryForm.getCategoryId());
		categoryJsonResponse.setCategoryName(categoryForm.getCategoryName());

		if (errorResult.hasErrors()) {
			Map<String, String> errors = errorResult.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
			categoryJsonResponse.setSuccess(false);
			categoryJsonResponse.setMessage("入力エラーがあります");
			categoryJsonResponse.setErrors(errors);

			return categoryJsonResponse;
		}

		Category category = categoryService.editCategory(categoryForm);

		if (category == null) {
			throw new RuntimeException("更新に失敗しました");
		} else {
			categoryJsonResponse.setSuccess(true);
			categoryJsonResponse.setMessage("更新しました");
		}

		return categoryJsonResponse;
	}

	/**
	 * Idをキーに論理削除
	 * @param categoryId
	 * @param mav
	 * @return
	 */
	@PostMapping("/category/{categoryId}/delete")
	public ModelAndView delete(@PathVariable Long categoryId, ModelAndView mav) {
		Category category = categoryService.deleteCategory(categoryId);

		mav.setViewName("redirect:/master/category");
		return mav;
	}
}
