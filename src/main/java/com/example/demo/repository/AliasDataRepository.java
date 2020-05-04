package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.AliasData;

public interface AliasDataRepository extends JpaRepository<AliasData, Long>{
	AliasData findOneById(Long id);
}
