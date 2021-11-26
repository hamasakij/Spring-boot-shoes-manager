package com.coltware.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "size_master")
public class Size extends BaseModel {


	/**
	 * サイズID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sizeId;
	
	/**
	 * サイズ
	 */
	private Double size;
}
