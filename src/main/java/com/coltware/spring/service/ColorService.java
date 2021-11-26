package com.coltware.spring.service;

import java.util.List;

import com.coltware.spring.model.Color;

/**
 * カラー サービス
 */
public interface ColorService {

	/**
	 * カラー一覧
	 * 
	 * @return
	 */
	public List<Color> getColors();
}
