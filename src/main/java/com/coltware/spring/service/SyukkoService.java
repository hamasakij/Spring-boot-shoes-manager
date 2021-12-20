package com.coltware.spring.service;

import java.util.List;

import com.coltware.spring.dto.SyukkoDto;
import com.coltware.spring.form.SyukkoForm;
import com.coltware.spring.model.Syukko;

/**
 * 出庫 サービス
 */
public interface SyukkoService {

	/**
	 * 出庫の一覧表示
	 * 
	 * @return
	 */
	public List<SyukkoDto> getInventory();

	/**
	 * 在庫からの出庫、出庫する個数を送る
	 * @param syukkoForm
	 * @return
	 */
	public Syukko doInsert(SyukkoForm syukkoForm);
	
	/**
	 * 出庫する個数を送る
	 * 
	 * @param syukkoForm
	 * @return
	 */
	public List<Syukko> doInsert(List<SyukkoForm> syukkoForm);
}
