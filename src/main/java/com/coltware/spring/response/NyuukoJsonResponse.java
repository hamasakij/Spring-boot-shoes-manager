package com.coltware.spring.response;

import java.util.Date;

import lombok.Data;

@Data
public class NyuukoJsonResponse extends JsonResponse{

	/**
	 * 入庫id
	 */
	private Long id;
	
	/**
	 * 商品id
	 */
	private Long productId;
	
	/**
	 * 入庫数
	 */
	private Long quantity;
	
	/**
	 * 入庫日
	 */
	private Date nyuukoDate;
}
