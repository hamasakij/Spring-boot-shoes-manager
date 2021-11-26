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

import com.coltware.spring.form.MakerForm;
import com.coltware.spring.form.MakerSearchForm;
import com.coltware.spring.form.valid.GroupOrder;
import com.coltware.spring.model.Maker;
import com.coltware.spring.response.MakerJsonResponse;
import com.coltware.spring.service.MakerService;

@Controller
@RequestMapping("/master")
public class MakerController {

	/**
	 * メーカーのサービスクラス
	 */
	@Autowired
	private MakerService makerService;

	/**
	 * メーカー一覧の取得
	 * @param makerSearchForm
	 * @param mav
	 * @return
	 */
	@GetMapping("/maker")
	public ModelAndView index(@ModelAttribute MakerSearchForm makerSearchForm, ModelAndView mav) {
		List<Maker> makerList = makerService.getMakers(makerSearchForm);
		List<Maker> makers = makerService.getMakers();
		
		
		mav.addObject("makers", makerList);
		mav.addObject("maker",makers);
		mav.addObject("makerForm", new MakerForm());
		// 表示するビュー
		mav.setViewName("master/maker/index");

		return mav;
	}

	/**
	 * メーカー新規作成
	 * @param makerForm
	 * @param errorResult
	 * @return
	 */
	@PostMapping("/maker/create")
	@ResponseBody
	public MakerJsonResponse create(@Validated(GroupOrder.class) @RequestBody MakerForm makerForm,
			BindingResult errorResult) {
		MakerJsonResponse makerJsonResponse = new MakerJsonResponse();
		makerJsonResponse.setMakerName(makerForm.getMakerName());

		if (errorResult.hasErrors()) {
			Map<String, String> errors = errorResult.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
			makerJsonResponse.setSuccess(false);
			makerJsonResponse.setMessage("入力エラーがあります");
			makerJsonResponse.setErrors(errors);
			
			return makerJsonResponse;
		}
		Maker maker = makerService.doInsert(makerForm);

		if (maker == null) {
			throw new RuntimeException("新規作成に失敗しました");
		} else {
			makerJsonResponse.setSuccess(true);
			makerJsonResponse.setMessage("新規作成しました");

		}
		return makerJsonResponse;
	}

	/**
	 * メーカーの更新
	 * @param makerId
	 * @param makerForm
	 * @param errorResult
	 * @return
	 */
	@PostMapping("/maker/{makerId}/edit")
	@ResponseBody
	public MakerJsonResponse edit(@Validated(GroupOrder.class) @PathVariable Long makerId,
			@RequestBody MakerForm makerForm, BindingResult errorResult) {

		MakerJsonResponse makerJsonResponse = new MakerJsonResponse();
		makerJsonResponse.setMakerId(makerForm.getMakerId());
		makerJsonResponse.setMakerName(makerForm.getMakerName());

		if (errorResult.hasErrors()) {
			Map<String, String> errors = errorResult.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
			makerJsonResponse.setSuccess(false);
			makerJsonResponse.setMessage("入力エラーがあります");
			makerJsonResponse.setErrors(errors);

			return makerJsonResponse;
		}
		Maker maker = makerService.editMaker(makerForm);

		if (maker == null) {
			throw new RuntimeException("更新に失敗しました");
		} else {
			makerJsonResponse.setSuccess(true);
			makerJsonResponse.setMessage("更新しました");
		}
		return makerJsonResponse;
	}

	/**
	 * Idをキーにメーカーを削除
	 * @param makerId
	 * @param mav
	 * @return
	 */
	@PostMapping("/maker/{makerId}/delete")
	public ModelAndView delete(@PathVariable Long makerId, ModelAndView mav) {
		Maker maker = makerService.deletedMaker(makerId);

		mav.setViewName("redirect:/master/maker");
		return mav;
	}
}
