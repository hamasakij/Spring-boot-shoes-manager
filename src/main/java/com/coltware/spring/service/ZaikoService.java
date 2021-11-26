package com.coltware.spring.service;

import java.util.List;

import com.coltware.spring.dto.ZaikoDto;
import com.coltware.spring.form.ZaikoSearchForm;

/**
 * 在庫 サービス
 */
public interface ZaikoService {

	/**
	 * 在庫一覧
	 * 
	 * @param zaikoSearchForm
	 * @return
	 */
	public List<ZaikoDto> getInventory(ZaikoSearchForm zaikoSearchForm);
}
