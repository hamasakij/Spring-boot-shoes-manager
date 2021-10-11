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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/**サイズID
	 * 
	 */
	private Long sizeId;
	/**サイズ
	 * 
	 */
	private Double size;
}
