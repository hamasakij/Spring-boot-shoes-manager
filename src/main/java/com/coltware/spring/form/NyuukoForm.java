package com.coltware.spring.form;

import java.util.Date;

import lombok.Data;

@Data
public class NyuukoForm {

	/**
	 * 入庫Id
	 */
	private Long id;
	
	/**
	 * 商品Id
	 */
	private Long productId;
	
	/**
	 * 入庫数
	 */
	private Long quantiry;
	
	/**
	 * 入庫日
	 */
	private Date nyuukoDate;
}
