package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SubjectDTO;
import com.example.demo.model.Subject;
import com.example.demo.repository.SubjectRepository;

@Service
public class SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;
	
	public Subject findOne(Long id) {
		return subjectRepository.findOneById(id);
	}
	
	public List<Subject> findAll(){
		return subjectRepository.findAll();
	}
	
	public Subject save(Subject subject) {
		return subjectRepository.save(subject);
	}
	
	public Subject convertFromDTO(SubjectDTO subjectDTO) {
		Subject subject = new Subject();
		
		subject.setCommonName(subjectDTO.getCommonName());
		subject.setSurname(subjectDTO.getSurname());
		subject.setGivenName(subjectDTO.getGivenName());
		subject.setOrganizationUnit(subjectDTO.getOrganizationUnit());
		subject.setOrganization(subjectDTO.getOrganization());
		subject.setCountry(subjectDTO.getCountry());
		subject.setEmail(subjectDTO.getEmail());
		subject.setIsCA(subjectDTO.getIsCA());
		subject.setHasCertificate(subjectDTO.getHasCertificate());
		
		return subject;
	}
	
}
