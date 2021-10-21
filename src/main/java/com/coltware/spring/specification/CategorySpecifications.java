package com.coltware.spring.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import com.coltware.spring.model.Category;

/**
 * カテゴリの検索条件
 *
 */
public class CategorySpecifications {

	/**
	 * 選択したカテゴリと一緒のもの
	 * 
	 * @param categoryId
	 * @return
	 */
	public static Specification<Category> categoryIdContains(Long categoryId) {
		return ObjectUtils.isEmpty(categoryId) ? null : new Specification<Category>() {

			@Override
			public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("categoryId"), categoryId);
			}
		};

	}

	/**
	 * カテゴリ名を含む検索
	 * 
	 * @param categoryName
	 * @return
	 */
	public static Specification<Category> categoryNameContains(String categoryName) {
		return ObjectUtils.isEmpty(categoryName) ? null : new Specification<Category>() {

			@Override
			public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.like(root.get("categoryName"), "%" + categoryName + "%");
			}
		};
	}

	/**
	 * 論理削除
	 * @param deleted
	 * @return
	 */
	public static Specification<Category> deletedContains(Boolean deleted) {
		deleted = !ObjectUtils.isEmpty(deleted) && deleted;
		return deleted ? null : new Specification<Category>() {

			@Override
			public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("deleted"), 0);
			}
		};
	}
}
