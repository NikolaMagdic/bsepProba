package com.example.demo.model;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Certificate {

	private Subject subject;
	private SubjectData subjectData;
	private IssuerData issuerData;
	private KeyPair keyPair;
	private Date startDate;
	private Date endDate;
	
	
	public Certificate() {}
	
	public Certificate(Subject subject, Date startDate, Date endDate) {
		Security.addProvider(new BouncyCastleProvider());
		this.startDate = startDate;
		this.endDate = endDate;
		this.subject = subject;
		
		this.subjectData = generateSubjectData(subject);
		
	}
	
	private SubjectData generateSubjectData(Subject subject) {
		try {
			KeyPair keyPairSubject = generateKeyPair();
			
			//Datumi od kad do kad vazi sertifikat
			SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = iso8601Formater.parse("2017-12-31");
			Date endDate = iso8601Formater.parse("2022-12-31");
			
			//Serijski broj sertifikata
			String sn="1";
			//klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
			X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
		    builder.addRDN(BCStyle.CN, subject.getCommonName());
		    builder.addRDN(BCStyle.SURNAME, subject.getSurname());
		    builder.addRDN(BCStyle.GIVENNAME, subject.getGivenName());
		    builder.addRDN(BCStyle.O, subject.getOrganization());
		    builder.addRDN(BCStyle.OU, subject.getOrganizationUnit());
		    builder.addRDN(BCStyle.C, subject.getCountry());
		    builder.addRDN(BCStyle.E, subject.getEmail());
		    //UID (USER ID) je ID korisnika
		    builder.addRDN(BCStyle.UID, subject.getUid().toString());
		    
		    //Kreiraju se podaci za sertifikat, sto ukljucuje:
		    // - javni kljuc koji se vezuje za sertifikat
		    // - podatke o vlasniku
		    // - serijski broj sertifikata
		    // - od kada do kada vazi sertifikat
		    
		    SubjectData sd = new SubjectData(keyPairSubject.getPublic(), builder.build(), sn);

		    return sd;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	private KeyPair generateKeyPair() {
        try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA"); 
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			keyGen.initialize(2048, random);
			return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
        return null;
	}

	public SubjectData getSubjectData() {
		return subjectData;
	}

	public void setSubjectData(SubjectData subjectData) {
		this.subjectData = subjectData;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public IssuerData getIssuerData() {
		return issuerData;
	}

	public void setIssuerData(IssuerData issuerData) {
		this.issuerData = issuerData;
	}
	
	
}
