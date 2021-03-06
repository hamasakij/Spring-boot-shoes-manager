package com.coltware.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "zaiko")
public class Zaiko extends BaseModel {

	
	/**
	 * 在庫Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 商品Id
	 */
	@Column(name = "product_id")
	private Long productId;
	
	/**
	 * 在庫数
	 */
	private Long inventoryCount;
	
	/**
	 * 商品
	 */
	@OneToOne(optional = false)
	@JoinColumn(name = "product_id", insertable = false, updatable = false)
	private Product product;
	
	





}
