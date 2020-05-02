package com.example.demo.dto;

import com.example.demo.model.Subject;

public class SubjectDTO {

    private String commonName;
    private String surname;	
    private String givenName;	
    private String organization;	
    private String organizationUnit;	
    private String country;	
    private String email;
    
    public SubjectDTO() {
    	
    }
    
	public SubjectDTO(String commonName, String surname, String givenName, String organization, String organizationUnit,
			String country, String email) {
		super();
		this.commonName = commonName;
		this.surname = surname;
		this.givenName = givenName;
		this.organization = organization;
		this.organizationUnit = organizationUnit;
		this.country = country;
		this.email = email;
	}
	
	public SubjectDTO(Subject s) {
		super();
		this.commonName = s.getCommonName();
		this.surname = s.getSurname();
		this.givenName = s.getGivenName();
		this.organization = s.getOrganization();
		this.organizationUnit = s.getOrganizationUnit();
		this.country = s.getCountry();
		this.email = s.getEmail();
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
