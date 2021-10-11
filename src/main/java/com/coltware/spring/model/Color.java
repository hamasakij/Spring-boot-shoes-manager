package com.coltware.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "color_master")
public class Color extends BaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/**カラーID
	 * 
	 */
	private Long colorId;
	/**カラー名
	 * 
	 */
	private String color;
}
