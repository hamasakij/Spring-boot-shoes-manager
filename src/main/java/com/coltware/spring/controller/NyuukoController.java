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

import com.coltware.spring.dto.NyuukoDto;
import com.coltware.spring.form.NyuukoForm;
import com.coltware.spring.form.valid.GroupOrder;
import com.coltware.spring.model.Nyuuko;
import com.coltware.spring.response.NyuukoJsonResponse;
import com.coltware.spring.service.NyuukoService;

@Controller
@RequestMapping("/system")
public class NyuukoController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 入庫 サービスクラス
	 */
	@Autowired
	private NyuukoService nyuukoService;

	/**
	 * 入庫画面を表示
	 * 
	 * @param mav
	 * @return
	 */
	@GetMapping("/nyuuko")
	public ModelAndView index(ModelAndView mav) {
		
		List<NyuukoDto> nyuuko = nyuukoService.getInventory();
		mav.addObject("nyuuko", nyuuko);
		mav.setViewName("/system/nyuuko/index");

		return mav;
	}

//	@PostMapping("/nyuuko/order")
//	public ModelAndView insert(@ModelAttribute NyuukoForm nyuukoForm, ModelAndView mav) {
//
//		Nyuuko nyuuko = nyuukoService.doInsert(nyuukoForm);
//
//		mav.setViewName("redirect:/system/zaiko");
//		return mav;
//	}
	@PostMapping("/nyuuko/check")
	@ResponseBody
	public NyuukoJsonResponse insert(@Validated(GroupOrder.class) @RequestBody List<NyuukoForm> nyuukoForm,
			BindingResult errorResult) {

		
		NyuukoJsonResponse nyuukoJsonResponse = new NyuukoJsonResponse();
		

		

//		if (errorResult.hasErrors()) {
//			Map<String, String> errors = errorResult.getFieldErrors().stream()
//					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
//			nyuukoJsonResponse.setSuccess(false);
//			nyuukoJsonResponse.setMessage("入力エラーがあります");
//			nyuukoJsonResponse.setErrors(errors);
//
//			return nyuukoJsonResponse;
//		}

		List<Nyuuko> nyuuko = nyuukoService.doInsert(nyuukoForm);
		if (nyuuko == null) {
			throw new RuntimeException("新規作成に失敗しました");
		} else {
			nyuukoJsonResponse.setSuccess(true);
			nyuukoJsonResponse.setMessage("新規作成に成功しました");
		}
		logger.debug("取得{}",nyuuko);

		return nyuukoJsonResponse;
	}
}
