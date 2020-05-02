package com.example.demo.controller;

import java.security.cert.X509Certificate;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.certificates.CertificateGenerator;
import com.example.demo.dto.CertificateDTO;
import com.example.demo.model.Certificate;

@RestController
@RequestMapping(value = "api/certificate")
public class CertificateController {
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CertificateDTO> addCertificate(@RequestBody CertificateDTO certificateDTO){
		
		if(certificateDTO.getClass() == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		
		Certificate certificate = new Certificate(certificateDTO.getSubject(), certificateDTO.getStartDate(), certificateDTO.getEndDate());
		
		CertificateGenerator cg = new CertificateGenerator();
		X509Certificate cert = cg.generateCertificate(certificate.getSubjectData(),  certificate.getIssuerData(),  certificateDTO);
		
		
		
		return new ResponseEntity<>(certificateDTO, HttpStatus.CREATED);
		
	}
	
}
