package com.coltware.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "category_master")
public class Category extends BaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/**
	 * カテゴリID
	 * 
	 */
	private Long categoryId;
	/**
	 * カテゴリ名
	 * 
	 */
	private String categoryName;

	/**
	 * 削除
	 */
	private Boolean deleted;
}
