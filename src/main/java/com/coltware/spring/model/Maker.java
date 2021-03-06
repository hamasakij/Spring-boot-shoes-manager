package com.coltware.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "maker_master")
public class Maker extends BaseModel{

	/**
	 * メーカーID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long makerId;
	
	/**
	 * メーカー名
	 */
	private String makerName;
	
	/**
	 * 削除
	 */
	private Boolean deleted;
}
