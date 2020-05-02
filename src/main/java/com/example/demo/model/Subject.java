package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
	
	@Column(name = "commonname")
    private String commonName;
	
	@Column(name = "surname")
    private String surname;
	
	@Column(name = "givenname")
    private String givenName;
	
	@Column(name = "organization")
    private String organization;
	
	@Column(name = "organizationunit")
    private String organizationUnit;
	
	@Column(name = "country")
    private String country;
	
	@Column(name = "email")
    private String email;
    
    public Subject() {
    	
    }

	public Subject(String commonName, String surname, String givenName,
			String organization, String organizationUnit, String country, String email) {
		super();
		this.commonName = commonName;
		this.surname = surname;
		this.givenName = givenName;
		this.organization = organization;
		this.organizationUnit = organizationUnit;
		this.country = country;
		this.email = email;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getOrganizationUnit() {
		return organizationUnit;
	}

	public void setOrganizationUnit(String organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
}
