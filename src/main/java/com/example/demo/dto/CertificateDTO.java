package com.example.demo.dto;

import java.util.Date;

import com.example.demo.model.Subject;

public class CertificateDTO {

	private Subject subjectRequest;
	private Subject subjectIssuer;
	private Date startDate;
	private Date endDate;
	private String alias;
	
	public CertificateDTO() {}

	public CertificateDTO(Subject subjectRequest, Subject subjectIssuer, Date startDate, Date endDate, String alias) {
		super();
		this.subjectRequest = subjectRequest;
		this.subjectIssuer = subjectIssuer;
		this.startDate = startDate;
		this.endDate = endDate;
		this.alias = alias;
	}

	public Subject getSubjectRequest() {
		return subjectRequest;
	}

	public void setSubjectRequest(Subject subjectRequest) {
		this.subjectRequest = subjectRequest;
	}

	public Subject getSubjectIssuer() {
		return subjectIssuer;
	}

	public void setSubjectIssuer(Subject subjectIssuer) {
		this.subjectIssuer = subjectIssuer;
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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	
	
}
