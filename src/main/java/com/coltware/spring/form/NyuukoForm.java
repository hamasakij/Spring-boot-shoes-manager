package com.coltware.spring.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
	private Long quantity;

	/**
	 * 入庫日
	 */
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date nyuukoDate;
}
