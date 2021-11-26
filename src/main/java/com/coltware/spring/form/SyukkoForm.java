package com.coltware.spring.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SyukkoForm {

	/**
	 * Id(出庫)
	 */
	private Long id;

	/**
	 * 商品Id
	 */
	private Long productId;

	/**
	 * 出庫数
	 */
	private Long quantity;

	/**
	 * 出庫日
	 */
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date syukkoDate;
}
