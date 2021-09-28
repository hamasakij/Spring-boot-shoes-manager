package com.coltware.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="product_master")
public class Product extends BaseModel {

	@Id
	@GeneratedValue
	//商品ID
	private Integer productId;
	//カテゴリID
	private Integer categoryId;
	//商品コード
	private Integer productCode;
	//メーカーID
	private Integer makerId;
	//商品名
	private String productName;
	//カラーID
	private Integer colorId;
	//サイズID
	private Integer sizeId;
	//値段
	private Integer price;
	//備考
	private String remarks;
	
	/** 商品IDを返す
	 * @return
	 */
	public Integer getProductId() {
		return productId;
	}
	/**商品IDを設定する
	 * @param productId
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	/**カテゴリIDを返す
	 * @return
	 */
	public Integer getCategoryId() {
		return categoryId;
	}
	/**カテゴリIDを設定する
	 * @param categoryId
	 */
	public void setCategoryID(Integer categoryId) {
		this.categoryId = categoryId;
	}
	/**商品コードを取得する
	 * @return
	 */
	public Integer getProductCode() {
		return productCode;
	}
	/**商品コードを設定する
	 * @param productCode
	 */
	public void setProductCode(Integer productCode) {
		this.productCode = productCode;
	}
	/**メーカーIDを取得する
	 * @return
	 */
	public Integer getMakerId() {
		return makerId;
	}
	/**メーカーIDを設定する
	 * @param makerId
	 */
	public void setMakerId(Integer makerId) {
		this.makerId = makerId;
	}
	/**商品名を取得する
	 * @return
	 */
	public String getProductName() {
		return productName;
	}
	/**商品名を設定する
	 * @param productName
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**カラーIDを取得する
	 * @return
	 */
	public Integer getColorId() {
		return colorId;
	}
	/**カラーIDを設定する
	 * @param colorId
	 */
	public void setColorId(Integer colorId) {
		this.colorId = colorId;
	}
	/**サイズIDを取得する
	 * @return
	 */
	public Integer getSizeId() {
		return sizeId;
	}
	/**サイズIDを設定する
	 * @param sizeId
	 */
	public void setSizeId(Integer sizeId) {
		this.sizeId = sizeId;
	}
	/**値段を取得する
	 * @return
	 */
	public Integer getPrice() {
		return price;
	}
	/**値段を設定する
	 * @param price
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}
	/**備考を取得する
	 * @return
	 */
	public String getRemarks() {
		return  remarks;
	}
	/**備考を設定する
	 * @param remarks
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}

