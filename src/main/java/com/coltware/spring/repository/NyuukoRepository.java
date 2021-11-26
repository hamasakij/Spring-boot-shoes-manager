package com.coltware.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.coltware.spring.model.Nyuuko;

/**
 * 入庫 レポジトリ
 */
@Repository
public interface NyuukoRepository extends JpaRepository<Nyuuko, Long>, JpaSpecificationExecutor<Nyuuko> {

}
