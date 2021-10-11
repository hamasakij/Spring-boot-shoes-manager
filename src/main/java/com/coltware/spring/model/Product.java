package com.coltware.spring.model;

import javax.persistence.CascadeType;
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
@Table(name = "product_master")
public class Product extends BaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/**
	 * 商品ID
	 * 
	 */
	private Long productId;
	
	/**
	 * カテゴリID
	 * 
	 */
	@Column(name = "category_id")
	private Long categoryId;
	
	/**
	 * 商品コード
	 * 
	 */
	private Long productCode;
	
	/**
	 * メーカーID
	 * 
	 */
	@Column(name = "maker_id")
	private Long makerId;
	
	/**
	 * 商品名
	 * 
	 */
	private String productName;
	
	/**
	 * カラーID
	 * 
	 */
	@Column(name = "color_id")
	private Long colorId;
	
	/**
	 * サイズID
	 * 
	 */
	@Column(name = "size_id")
	private Long sizeId;
	
	/**
	 * 値段
	 * 
	 */
	private Long price;
	
	/**
	 * 備考
	 * 
	 */
	private String remarks;

	/**
	 * サイズ
	 * 
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "size_id", insertable = false, updatable = false)
	private Size size;
	
	/**
	 * カラー
	 * 
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "color_id", insertable = false, updatable = false)
	private Color color;
	
	/**
	 * カテゴリ
	 * 
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id", insertable = false, updatable = false)
	private Category category;
	
	/**
	 * メーカー
	 * 
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "maker_id", insertable = false, updatable = false)
	private Maker maker;
	
	/**
     * サムネイル
     */
    private String thumbnail;

	
}