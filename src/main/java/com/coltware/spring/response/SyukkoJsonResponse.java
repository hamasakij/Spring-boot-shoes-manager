package com.coltware.spring.response;

import java.util.Date;

import lombok.Data;

@Data
public class SyukkoJsonResponse extends JsonResponse{

	/**
	 * 出庫id
	 */
	private Long id;
	
	/**
	 * 商品id
	 */
	private Long productId;
	
	/**
	 * 出庫数
	 */
	private Long quantity;
	
	/**
	 * 出庫日
	 */
	private Date syukkoDate;
}
