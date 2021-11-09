package com.coltware.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.coltware.spring.model.Zaiko;

@Repository
public interface ZaikoRepository extends JpaRepository<Zaiko, Long>, JpaSpecificationExecutor<Zaiko>{

}
