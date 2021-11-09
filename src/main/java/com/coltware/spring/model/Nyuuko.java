package com.coltware.spring.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "nyuuko")
public class Nyuuko extends BaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/**
	 * id(入庫)
	 */
	private Long id;

	/**
	 * 商品Id
	 */
	@Column(name = "product_id")
	private Long productId;
	
	/**
	 * 入庫数
	 */
	private Long quantity;
	
	/**
	 * 入庫日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date nyuukoDate;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id", insertable = false, updatable = false)
	private Product product;
}
