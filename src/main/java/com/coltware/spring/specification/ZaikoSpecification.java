package com.coltware.spring.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import com.coltware.spring.model.Zaiko;

/**
 * 検索条件
 *
 */
public class ZaikoSpecification {

	/**
	 * 商品名を含む検索 入力した文字を含むものを表示
	 * 
	 * @param productName
	 * @return
	 */
	public static Specification<Zaiko> nameContains(String productName) {
		return ObjectUtils.isEmpty(productName) ? null : new Specification<Zaiko>() {

			@Override
			public Predicate toPredicate(Root<Zaiko> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.like(root.join("product", JoinType.LEFT).get("productName"), "%" + productName + "%");
			}
		};
	}

	/**
	 * 商品コードを含む検索 入力した数字と一緒のものを表示
	 * 
	 * @param productCode
	 * @return
	 */
	public static Specification<Zaiko> codeContains(Long productCode) {
		return ObjectUtils.isEmpty(productCode) ? null : new Specification<Zaiko>() {

			@Override
			public Predicate toPredicate(Root<Zaiko> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.join("product", JoinType.LEFT).get("productCode"), productCode);
			}
		};
	}

	/**
	 * 値段を含む検索 以上の検索
	 * 
	 * @param minPrice
	 * @return
	 */
	public static Specification<Zaiko> minPriceContains(Long minPrice) {
		return ObjectUtils.isEmpty(minPrice) ? null : new Specification<Zaiko>() {

			@Override
			public Predicate toPredicate(Root<Zaiko> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.ge(root.join("product", JoinType.LEFT).get("price"), minPrice);
			}
		};
	}

	/**
	 * 
	 * 以下の検索
	 * 
	 * @param maxPrice
	 * @return
	 */
	public static Specification<Zaiko> maxPriceContains(Long maxPrice) {
		return ObjectUtils.isEmpty(maxPrice) ? null : new Specification<Zaiko>() {

			@Override
			public Predicate toPredicate(Root<Zaiko> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.le(root.join("product", JoinType.LEFT).get("price"), maxPrice);
			}
		};
	}

	/**
	 * カテゴリIdの検索 選択したカテゴリを一緒のもの
	 * 
	 * @param categoryId
	 * @return
	 */
	public static Specification<Zaiko> categoryIdContains(Long categoryId) {
		return ObjectUtils.isEmpty(categoryId) ? null : new Specification<Zaiko>() {

			@Override
			public Predicate toPredicate(Root<Zaiko> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				// 想定しているSQLと同じイメージで
				// select * from zaiko left join product_master on zaiko.product_id = product_master.product_id where product_master.category_id = ?
				return criteriaBuilder.equal(root.join("product", JoinType.LEFT).get("categoryId"), categoryId);
			}
		};
	}

	/**
	 * カラーIdの検索条件 選択したものと一緒のもの
	 * 
	 * @param colorId
	 * @return
	 */
	public static Specification<Zaiko> colorIdContains(Long colorId) {
		return ObjectUtils.isEmpty(colorId) ? null : new Specification<Zaiko>() {

			@Override
			public Predicate toPredicate(Root<Zaiko> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.join("product", JoinType.LEFT).get("colorId"), colorId);
			}
		};
	}

	/**
	 * メーカーIdの検索条件 選択したものと一緒のもの
	 * 
	 * @param colorId
	 * @return
	 */
	public static Specification<Zaiko> makerIdContains(Long makerId) {
		return ObjectUtils.isEmpty(makerId) ? null : new Specification<Zaiko>() {

			@Override
			public Predicate toPredicate(Root<Zaiko> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.join("product",JoinType.LEFT).get("makerId"),makerId);
			}
		};
	}

	/**
	 * サイズIdの検索条件 選択したものと一緒のもの
	 * 
	 * @param sizeId
	 * @return
	 */
	public static Specification<Zaiko> sizeIdContains(Long sizeId) {
		return ObjectUtils.isEmpty(sizeId) ? null : new Specification<Zaiko>() {

			@Override
			public Predicate toPredicate(Root<Zaiko> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.join("product", JoinType.LEFT).get("sizeId"), sizeId);
			}
		};
	}

	/**
	 * 削除されたものを表示しない deletedが1のものを表示させない
	 * 
	 * @param deleted
	 * @return
	 */
	public static Specification<Zaiko> deletedContains(Boolean deleted) {
		deleted = !ObjectUtils.isEmpty(deleted) && deleted;
		return deleted ? null : new Specification<Zaiko>() {

			@Override
			public Predicate toPredicate(Root<Zaiko> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.join("product", JoinType.LEFT).get("deleted"), 0);
			}

		};
	}
}
