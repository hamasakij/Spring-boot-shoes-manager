package com.coltware.spring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.coltware.spring.dto.ProductDto;
import com.coltware.spring.form.ProductForm;
import com.coltware.spring.form.ProductSearchForm;
import com.coltware.spring.form.valid.GroupOrder;
import com.coltware.spring.model.Category;
import com.coltware.spring.model.Color;
import com.coltware.spring.model.Maker;
import com.coltware.spring.model.Product;
import com.coltware.spring.model.Size;
import com.coltware.spring.service.CategoryService;
import com.coltware.spring.service.ColorService;
import com.coltware.spring.service.MakerService;
import com.coltware.spring.service.ProductService;
import com.coltware.spring.service.SizeService;

@Controller
@RequestMapping("/master")
public class ProductController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 商品 サービスクラス
	 */
	@Autowired
	private ProductService productService;

	/**
	 * カテゴリ サービスクラス
	 */
	@Autowired
	private CategoryService categoryService;

	/**
	 * サイズ サービスクラス
	 */
	@Autowired
	private SizeService sizeService;

	/**
	 * カラー サービスクラス
	 */
	@Autowired
	private ColorService colorService;

	/**
	 * メーカー サービスクラス
	 */
	@Autowired
	private MakerService makerService;

	/**
	 * 商品マスタ一覧表示
	 * 
	 * @param productSearchForm
	 * @param mav
	 * @return
	 */
	@GetMapping("/product")
	public ModelAndView index(@Validated(GroupOrder.class) @ModelAttribute ProductSearchForm productSearchForm, BindingResult errorResult,
			ModelAndView mav) {

		// 商品一覧取得
		// 商品情報をまとめる
		List<ProductDto> resultList = productService.getProducts(productSearchForm);
		
		// ModelAndViewに登録
		mav.addObject("products", resultList);
		logger.debug("取得しました{}",resultList);
		// プルダウンメニュー一覧取得
		List<Category> categoryList = categoryService.getCategorys();
		List<Size> sizeList = sizeService.getSizes();
		List<Color> colorList = colorService.getColors();
		List<Maker> makerList = makerService.getMakers();

		// それぞれを登録
		mav.addObject("categorys", categoryList);
		mav.addObject("sizes", sizeList);
		mav.addObject("colors", colorList);
		mav.addObject("makers", makerList);

		if (errorResult.hasErrors()) {
			mav.setViewName("master/product/index");
			return mav;
		}
		// 表示するビュー
		mav.setViewName("master/product/index");
		return mav;
	}

	/**
	 * 新規商品追加画面を表示
	 * 
	 * @param productForm
	 * @param mav
	 * @return
	 */
	@GetMapping("/product/create")
	public ModelAndView create(@ModelAttribute ProductForm productForm, ModelAndView mav) {
		// プルダウンメニュー一覧取得
		List<Category> categoryList = categoryService.getCategorys();
		List<Size> sizeList = sizeService.getSizes();
		List<Color> colorList = colorService.getColors();
		List<Maker> makerList = makerService.getMakers();
		// それぞれを登録
		mav.addObject("categorys", categoryList);
		mav.addObject("sizes", sizeList);
		mav.addObject("colors", colorList);
		mav.addObject("makers", makerList);
		// 表示するビュー
		mav.setViewName("master/product/create");
		return mav;
	}

	/**
	 * 新規登録した商品を生成する
	 * 
	 * @param productForm
	 * @param errorResult
	 * @param mav
	 * @return
	 */
	@PostMapping("/product/create")
	public ModelAndView insert(@Validated(GroupOrder.class) @ModelAttribute ProductForm productForm,
			BindingResult errorResult, ModelAndView mav) {

		// プルダウンメニュー一覧取得
		List<Category> categoryList = categoryService.getCategorys();
		List<Size> sizeList = sizeService.getSizes();
		List<Color> colorList = colorService.getColors();
		List<Maker> makerList = makerService.getMakers();
		// それぞれを登録
		mav.addObject("categorys", categoryList);
		mav.addObject("sizes", sizeList);
		mav.addObject("colors", colorList);
		mav.addObject("makers", makerList);
		if (errorResult.hasErrors()) {
			mav.setViewName("master/product/create");
			return mav;
		}
		Product product = productService.doInsert(productForm);

		mav.setViewName("redirect:/master/product");
		return mav;
	}

	/**
	 * 商品の詳細情報を表示
	 * 
	 * @param productId
	 * @param mav
	 * @return
	 */
	@GetMapping("/product/{productId}/detailEdit")
	public ModelAndView detail(@PathVariable Long productId, ModelAndView mav) {

		ProductForm productForm = productService.getDetail(productId);

		// プルダウンメニュー一覧取得
		List<Category> categoryList = categoryService.getCategorys();
		List<Size> sizeList = sizeService.getSizes();
		List<Color> colorList = colorService.getColors();
		List<Maker> makerList = makerService.getMakers();

		// それぞれを登録
		mav.addObject("productForm", productForm);
		mav.addObject("categorys", categoryList);
		mav.addObject("sizes", sizeList);
		mav.addObject("colors", colorList);
		mav.addObject("makers", makerList);

		mav.setViewName("master/product/detailEdit");
		return mav;
	}

	/**
	 * 商品の詳細情報の編集
	 * 
	 * @param productForm
	 * @param errorResult
	 * @param mav
	 * @param attributes
	 * @return
	 */
	@PostMapping("/product/detailEdit")
	public ModelAndView editUpdate(@Validated(GroupOrder.class) @ModelAttribute ProductForm productForm,
			BindingResult errorResult, ModelAndView mav) {
		// プルダウンメニュー一覧取得
		List<Category> categoryList = categoryService.getCategorys();
		List<Size> sizeList = sizeService.getSizes();
		List<Color> colorList = colorService.getColors();
		List<Maker> makerList = makerService.getMakers();
		// それぞれを登録
		mav.addObject("categorys", categoryList);
		mav.addObject("sizes", sizeList);
		mav.addObject("colors", colorList);
		mav.addObject("makers", makerList);
		// 入力チェック
		if (errorResult.hasErrors()) {
			mav.setViewName("master/product/detailEdit");
			return mav;
		}
		Product product = productService.detailUpdate(productForm);
		

		mav.setViewName("redirect:/master/product");
		return mav;
	}

	/**
	 * 削除するボタン 該当する行を削除する
	 * 
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
