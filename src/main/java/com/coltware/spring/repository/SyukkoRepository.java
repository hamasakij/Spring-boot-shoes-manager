package com.coltware.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.coltware.spring.model.Syukko;

/**
 * 出庫 レポジトリ
 */
@Repository
public interface SyukkoRepository extends JpaRepository<Syukko, Long>, JpaSpecificationExecutor<Syukko> {

}
