package com.coltware.spring.service;

import java.util.List;

import com.coltware.spring.dto.NyuukoDto;
import com.coltware.spring.form.NyuukoForm;
import com.coltware.spring.model.Nyuuko;

/**
 * 入庫 サービス
 */
public interface NyuukoService {

	/**
	 * 商品情報を表示
	 * 
	 * @return
	 */
	public List<NyuukoDto> getInventory();

	/**
	 * 入庫処理
	 * 
	 * @param nyuukoForm
	 * @return
	 */
	public List<Nyuuko> doInsert(List<NyuukoForm> nyuukoForm);
}
