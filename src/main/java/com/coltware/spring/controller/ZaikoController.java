package com.coltware.spring.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.coltware.spring.dto.ZaikoDto;
import com.coltware.spring.form.ProductForm;
import com.coltware.spring.form.SyukkoForm;
import com.coltware.spring.form.ZaikoSearchForm;
import com.coltware.spring.form.valid.GroupOrder;
import com.coltware.spring.model.Category;
import com.coltware.spring.model.Color;
import com.coltware.spring.model.Maker;
import com.coltware.spring.model.Product;
import com.coltware.spring.model.Size;
import com.coltware.spring.model.Syukko;
import com.coltware.spring.response.SyukkoJsonResponse;
import com.coltware.spring.service.CategoryService;
import com.coltware.spring.service.ColorService;
import com.coltware.spring.service.MakerService;
import com.coltware.spring.service.ProductService;
import com.coltware.spring.service.SizeService;
import com.coltware.spring.service.SyukkoService;
import com.coltware.spring.service.ZaikoService;

@Controller
@RequestMapping("/system")
public class ZaikoController {

	/**
	 * ログ
	 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 在庫 サービスクラス
	 */
	@Autowired
	private ZaikoService zaikoService;

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
	 * 出庫 サービスクラス
	 */
	@Autowired
	private SyukkoService syukkoService;

	/**
	 * 在庫一覧を表示
	 * @param zaikoSearchForm
	 * @param mav
	 * @return
	 */
	@GetMapping("/zaiko")
	public ModelAndView index(@ModelAttribute ZaikoSearchForm zaikoSearchForm, ModelAndView mav) {

		// 在庫のある商品を取得
		List<ZaikoDto> zaiko = zaikoService.getInventory(zaikoSearchForm);

		mav.addObject("zaiko", zaiko);

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
		mav.setViewName("system/zaiko/index");

		return mav;
	}

	/**
	 * 新規商品追加画面を表示
	 * @param productForm
	 * @param mav
	 * @return
	 */
	@GetMapping("/zaiko/create")
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
		mav.setViewName("system/zaiko/create");
		return mav;
	}

	/**
	 * 新規登録した商品を生成する
	 * @param productForm
	 * @param errorResult
	 * @param mav
	 * @return
	 */
	@PostMapping("/zaiko/create")
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
			mav.setViewName("system/zaiko/create");
			return mav;
		}
		Product product = productService.doInsert(productForm);

		mav.setViewName("redirect:/system/zaiko");
		return mav;
	}

	/**
	 * 商品の詳細情報を取得
	 * @param productId
	 * @param mav
	 * @return
	 */
	@GetMapping("/zaiko/{productId}/detail")
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

		mav.setViewName("system/zaiko/detail");
		return mav;
	}

	/**
	 * 出庫モーダルの処理
	 * @param syukkoForm
	 * @param errorResult
	 * @return
	 */
	@PostMapping("/syukko/{productId}/order")
	@ResponseBody
	public SyukkoJsonResponse insert(@Validated(GroupOrder.class) @RequestBody SyukkoForm syukkoForm,
			BindingResult errorResult) {

		logger.debug("取得できました{}", syukkoForm);
		
		SyukkoJsonResponse syukkoJsonResponse = new SyukkoJsonResponse();
		syukkoJsonResponse.setQuantity(syukkoForm.getQuantity());

		

		if (errorResult.hasErrors()) {
			Map<String, String> errors = errorResult.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
			syukkoJsonResponse.setSuccess(false);
			syukkoJsonResponse.setMessage("入力エラーがあります");
			syukkoJsonResponse.setErrors(errors);

			return syukkoJsonResponse;
		}

		Syukko syukko = syukkoService.doInsert(syukkoForm);
		if (syukko == null) {
			throw new RuntimeException("新規作成に失敗しました");
		} else {
			syukkoJsonResponse.setSuccess(true);
			syukkoJsonResponse.setMessage("新規作成に成功しました");
		}

		return syukkoJsonResponse;
	}

}
