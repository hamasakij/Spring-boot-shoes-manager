package com.coltware.spring.service;

import java.util.List;

import com.coltware.spring.model.Size;

public interface SizeService {

	/**
	 * サイズ一覧
	 * @param sizeId
	 * @return
	 */
	public List<Size> getSizes();
}
