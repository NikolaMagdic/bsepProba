package com.example.demo.dto;

import java.util.Date;

import com.example.demo.model.IssuerData;
import com.example.demo.model.Subject;

public class CertificateDTO {

	private Subject subject;
	private IssuerData issuerData;
	private Date startDate;
	private Date endDate;
	
	public CertificateDTO() {}

	public CertificateDTO(Subject subject, IssuerData issuerData, Date startDate, Date endDate) {
		super();
		this.subject = subject;
		this.issuerData = issuerData;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public IssuerData getIssuerData() {
		return issuerData;
	}

	public void setIssuerData(IssuerData issuerData) {
		this.issuerData = issuerData;
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
	
	
	
}
