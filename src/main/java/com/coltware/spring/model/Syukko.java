package com.coltware.spring.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "syukko")
public class Syukko {

	/**
	 * Id(出庫)
	 */
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
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
	private Date syukkoDate;
}
