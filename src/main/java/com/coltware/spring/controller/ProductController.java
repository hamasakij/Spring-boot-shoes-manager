package com.coltware.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.coltware.spring.dto.ProductDto;
import com.coltware.spring.form.ProductForm;
import com.coltware.spring.model.Product;
import com.coltware.spring.service.CategoryService;
import com.coltware.spring.service.ProductService;

@Controller
@RequestMapping("/master")
public class ProductController {

	/**商品 サービスクラス
	 * 
	 */
	@Autowired
	private ProductService productService;
	private CategoryService categoryServise;


	@GetMapping("/product")
	public ModelAndView index(@ModelAttribute Product productId, ModelAndView mav) {

		// 商品一覧取得	
		//商品情報をまとめる
		List<ProductDto> resultList = productService.getProducts(productId);

		
		// ModelAndViewに登録
		mav.addObject("products", resultList);

		// 表示するビュー
		mav.setViewName("master/product/index");

		return mav;
	}
	/**新規商品追加画面を表示
	 * @param mav
	 * @return
	 */
	@GetMapping("/product/create")
	public ModelAndView create (@ModelAttribute ProductForm productForm, ModelAndView mav) {
		mav.setViewName("master/product/create");
		return mav;
	}
	/**
	 * 新規登録した商品を生成する
	 * @return
	 */
	@PostMapping("/product/create")
	public ModelAndView Insert(@ModelAttribute ProductForm productForm, ModelAndView mav) {
		Product product = productService.doInsert(productForm);
		mav.setViewName("redirect:/master/product");
		return mav;
	}
	
	/**
	 * 削除するボタン
	 * 該当する行を削除する
	 * @param productId
	 * @param mav
	 * @return
	 */
	@PostMapping("/product/{productId}/delete")
	public ModelAndView delete(@PathVariable Long productId, ModelAndView mav) {
		Product product = productService.deleteProduct(productId);
		
		mav.setViewName("redirect:/master/product");
		return mav;
	}

}
