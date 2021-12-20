package com.coltware.spring.response;

import java.util.Date;

import lombok.Data;

@Data
public class SyukkoJsonResponse extends JsonResponse {

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
	@SuppressWarnings("unused")
	private Date syukkoDate;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	/**
	 * 出庫id
	 */
	private Long id;

}
