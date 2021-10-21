package com.coltware.spring.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import com.coltware.spring.model.Maker;

/**
 * メーカーの検索条件
 *
 */
public class MakerSpecifications {

	/**
	 * 選択したメーカーと一緒のもの
	 * 
	 * @param makerId
	 * @return
	 */
	public static Specification<Maker> makerIdContains(Long makerId) {
		return ObjectUtils.isEmpty(makerId) ? null : new Specification<Maker>() {

			@Override
			public Predicate toPredicate(Root<Maker> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("makerId"), makerId);
			}
		};
	}
	
	/**
	 * メーカー名を含む検索
	 * @param makerName
	 * @return
	 */
	public static Specification<Maker> makerNameContains(String makerName) {
		return ObjectUtils.isEmpty(makerName) ? null : new Specification<Maker>() {

			@Override
			public Predicate toPredicate(Root<Maker> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.like(root.get("makerName"), "%" + makerName + "%");
			}
		};
	}
	
	/**
	 * 論理削除
	 * @param deleted
	 * @return
	 */
	public static Specification<Maker> deletedContains(Boolean deleted){
		deleted = !ObjectUtils.isEmpty(deleted) && deleted;
		return deleted ? null : new Specification<Maker>() {

			@Override
			public Predicate toPredicate(Root<Maker> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("deleted"), 0);
			}
			
		};
	}
}
