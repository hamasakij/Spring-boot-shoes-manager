package com.coltware.spring.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SyukkoForm {

	

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
	 * Id(出庫)
	 */
	private Long id;
}
