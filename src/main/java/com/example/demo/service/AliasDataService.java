package com.example.demo.service;

import java.util.List;

import org.hibernate.sql.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AliasData;
import com.example.demo.model.Certificate;
import com.example.demo.repository.AliasDataRepository;

@Service
public class AliasDataService {

	@Autowired
	private AliasDataRepository aliasDataRepository;
	
	public AliasDataService(){}
	
	public AliasData findOne(Long id){
		return aliasDataRepository.findOneById(id);
	}
	public AliasData save(AliasData aliasData) {
		return aliasDataRepository.save(aliasData);
	}
	public List<AliasData> findAll(){
		return aliasDataRepository.findAll();
	}
	
}
