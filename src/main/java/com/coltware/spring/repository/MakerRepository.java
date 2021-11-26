package com.coltware.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.coltware.spring.model.Maker;

/**
 * メーカー レポジトリ
 */
@Repository
public interface MakerRepository extends JpaRepository<Maker, Long>, JpaSpecificationExecutor<Maker> {

}
