package com.example.demo.controller;

import java.io.IOException;
import java.security.KeyPair;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.certificates.CertificateGenerator;
import com.example.demo.dto.CertificateDTO;
import com.example.demo.dto.SubjectDTO;
import com.example.demo.enums.CertificateType;
import com.example.demo.model.AliasData;
import com.example.demo.model.Certificate;
import com.example.demo.model.IssuerData;
import com.example.demo.model.Subject;
import com.example.demo.model.SubjectData;
import com.example.demo.service.AliasDataService;
import com.example.demo.service.CertificateService;
import com.example.demo.service.SubjectService;

@RestController
@RequestMapping(value = "api/certificate")
public class CertificateController {
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private CertificateService certificateService;
	@Autowired
	private AliasDataService aliasDataService;
	
	//Vraca sve subjekte koji mogu biti issueri, samo one koji su CA
	@GetMapping(value = "/issuers")
	public ResponseEntity<List<SubjectDTO>> getIssuers(){
		
		List<SubjectDTO> issuersDTO = new ArrayList<>();
		List<Subject> issuers = subjectService.findAll();
		
		for (Subject issuer : issuers) {
			if(issuer.getIsCA())
				issuersDTO.add(new SubjectDTO(issuer));
		}
		
		return new ResponseEntity<>(issuersDTO, HttpStatus.OK);
		
	}
	
	//Svi ponudjeni subjecti za sertifikat
	@GetMapping(value = "/subjects")
	public ResponseEntity<List<SubjectDTO>> getSubjects(){
		
		List<SubjectDTO> subjectsDTO = new ArrayList<>();
		List<Subject> subjects = subjectService.findAll();
		
		for (Subject subject : subjects) {
			if(!subject.getHasCertificate())
				subjectsDTO.add(new SubjectDTO(subject));
		}
		
		return new ResponseEntity<>(subjectsDTO, HttpStatus.OK);
		
	}
	
	//Pravljenje self-signed sertifikata
	@PostMapping(value = "/createRoot", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CertificateDTO> addRootCertificate(@RequestBody CertificateDTO certificateDTO){
		
		//Ovo ne znam gde da stavim, ima u primeru sa vezbi
		Security.addProvider(new BouncyCastleProvider());
		
		//Da znamo u koji keystore da ga bacimo
		CertificateType ct = CertificateType.ROOT;
		
		if(certificateDTO.getClass() == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Subject subject = subjectService.findOne((Long)certificateDTO.getSubjectId());
		
		if(subject == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		subject.setIsCA(true);
		subject.setHasCertificate(true);
		subjectService.save(subject);
		
		Subject issuer = subjectService.findOne((Long)certificateDTO.getIssuerId());
		
		KeyPair keyPair = certificateService.generateKeyPair();
		
		SubjectData subjectData = certificateService.generateSubjectData(subject);
		IssuerData issuerData = certificateService.generateIssuerData(keyPair.getPrivate(), issuer);
		
		CertificateGenerator cg = new CertificateGenerator();
		X509Certificate cert = cg.generateCertificate(subjectData, issuerData, certificateDTO);

		try {
			certificateService.createCertificate(certificateDTO.getAlias(), certificateDTO.getPassword(),  cert, issuerData.getPrivateKey(), ct);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Certificate c = certificateService.convertFromDTO(certificateDTO);
		certificateService.save(c);
		
		AliasData ad= new AliasData();
		ad.setId(22222L);
		ad.setAlias(certificateDTO.getAlias());
		aliasDataService.save(ad);
		
		return new ResponseEntity<>(certificateDTO, HttpStatus.CREATED);
		
	}
	
	//Pravljenje potpisanih sertifikata
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CertificateDTO> addCertificate(@RequestBody CertificateDTO certificateDTO){
		
		Security.addProvider(new BouncyCastleProvider());
		
		//Da znamo u koji keystore da ga bacimo
		CertificateType ct = CertificateType.INTERMEDIATE;
		
		if(certificateDTO.getClass() == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Subject subject = subjectService.findOne((Long)certificateDTO.getSubjectId());
		
		if(subject == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		subject.setIsCA(false);
		subject.setHasCertificate(true);
		subjectService.save(subject);
		
		Subject issuer = subjectService.findOne((Long)certificateDTO.getIssuerId());
		
		KeyPair keyPair = certificateService.generateKeyPair();
		
		SubjectData subjectData = certificateService.generateSubjectData(subject);
		IssuerData issuerData = certificateService.generateIssuerData(keyPair.getPrivate(), issuer);
		
		CertificateGenerator cg = new CertificateGenerator();
		X509Certificate cert = cg.generateCertificate(subjectData, issuerData, certificateDTO);

		try {
			certificateService.createCertificate(certificateDTO.getAlias(), certificateDTO.getPassword(), cert, issuerData.getPrivateKey(), ct);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Certificate c = certificateService.convertFromDTO(certificateDTO);
		certificateService.save(c);
		
		return new ResponseEntity<>(certificateDTO, HttpStatus.CREATED);
	}
	
	
}
