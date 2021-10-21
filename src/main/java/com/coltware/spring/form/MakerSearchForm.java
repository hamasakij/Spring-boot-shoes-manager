package com.coltware.spring.form;

import lombok.Data;

@Data
public class MakerSearchForm {

	/**
	 * メーカーid
	 */
	private Long makerId;
	
	/**
	 * メーカー名
	 */
	private String makerName;
	
	/**
	 * 削除
	 */
	private Boolean deleted;
}
