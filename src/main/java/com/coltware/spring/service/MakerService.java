package com.coltware.spring.service;

import java.util.List;

import com.coltware.spring.form.MakerForm;
import com.coltware.spring.form.MakerSearchForm;
import com.coltware.spring.model.Maker;

public interface MakerService {

	/**
	 * メーカーのプルダウンメニュー取得
	 * @return
	 */
	public List<Maker> getMakers();
	/**
	 * メーカー一覧
	 * @return
	 */
	public List<Maker> getMakers(MakerSearchForm makerSearchForm);
	
	/**
	 * メーカー作成
	 * @param makerForm
	 * @return
	 */
	public Maker doInsert(MakerForm makerForm);
	
	/**
	 * Idをもとにカテゴリの情報を取得
	 * @param makerId
	 * @return
	 */
	public MakerForm getMaker(Long makerId);
	/**
	 * メーカーの情報を更新
	 * @param makerForm
	 * @return
	 */
	public Maker editMaker(MakerForm makerForm);
	/**
	 * Idをもとにメーカーを削除
	 * @param makerId
	 * @return
	 */
	public Maker deletedMaker(Long makerId);
	
}
