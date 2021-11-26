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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.coltware.spring.dto.SyukkoDto;
import com.coltware.spring.form.SyukkoForm;
import com.coltware.spring.form.valid.GroupOrder;
import com.coltware.spring.model.Syukko;
import com.coltware.spring.response.SyukkoJsonResponse;
import com.coltware.spring.service.SyukkoService;

@Controller
@RequestMapping("/system")
public class SyukkoController {

	/**
	 * 出庫 サービスクラス
	 */
	@Autowired
	private SyukkoService syukkoService;

	/**
	 * 出庫登録画面表示
	 * 
	 * @param mav
	 * @return
	 */
	@GetMapping("/syukko")
	public ModelAndView index(ModelAndView mav) {

		List<SyukkoDto> syukko = syukkoService.getInventory();
		mav.addObject("syukko", syukko);
		mav.setViewName("/system/syukko/index");

		return mav;
	}

	@PostMapping("/syukko/{productId}/check")
	@ResponseBody
	public SyukkoJsonResponse insert(@Validated(GroupOrder.class) @RequestBody SyukkoForm syukkoForm,
			BindingResult errorResult) {

		
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
