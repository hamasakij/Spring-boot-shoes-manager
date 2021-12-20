package com.coltware.spring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

	@PostMapping("/syukko/check")
	@ResponseBody
	public SyukkoJsonResponse insert(@Validated(GroupOrder.class) @RequestBody List<SyukkoForm> syukkoForm,
			BindingResult errorResult) {
		logger.debug("取得{}", syukkoForm);
		SyukkoJsonResponse syukkoJsonResponse = new SyukkoJsonResponse();
	
		

		
		
//		if (errorResult.hasErrors()) {
//			Map<String, String> errors = errorResult.getFieldErrors().stream()
//					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
//			syukkoJsonResponse.setSuccess(false);
//			syukkoJsonResponse.setMessage("入力エラーがあります");
//			syukkoJsonResponse.setErrors(errors);
//
//			return syukkoJsonResponse;
//		}

		List<Syukko> syukko = syukkoService.doInsert(syukkoForm);
		logger.debug("syukkoの内容{}",syukkoForm);
		if (syukko == null) {
			throw new RuntimeException("新規作成に失敗しました");
		} else {
			syukkoJsonResponse.setSuccess(true);
			syukkoJsonResponse.setMessage("新規作成に成功しました");
		}

		logger.debug("取得{}", syukko);

		return syukkoJsonResponse;
	}
}
