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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/**メーカーID
	 * 
	 */
	private Long makerId;
	/**メーカー名
	 * 
	 */
	private String makerName;
}
