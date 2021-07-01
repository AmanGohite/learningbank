package com.banking.leaningbank.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

@Entity
@Table(name="cust_beneficary")
@ApiModel(description="All Details about Beneficary Banking API")

public class Beneficiary {
	
	public Beneficiary() {}
	
	


	public Beneficiary( Integer customerId, String beneficaryName, LocalDate dateAdded) {
		super();
	
		this.customerId = customerId;
		this.beneficaryName = beneficaryName;
		this.dateAdded = dateAdded;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer beneficaryId;
	
	@Column(name="cust_id")
	private Integer customerId;
	
	
	@Column(name="beneficary_name")
	private String beneficaryName;
	
	@Column(name="date_added")
	private LocalDate dateAdded;

	
	
	public Integer getBeneficaryId() {
		return beneficaryId;
	}



	public void setBeneficaryId(Integer beneficaryId) {
		this.beneficaryId = beneficaryId;
	}



	public Integer getCustomerId() {
		return customerId;
	}



	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}



	public String getBeneficaryName() {
		return beneficaryName;
	}



	public void setBeneficaryName(String beneficaryName) {
		this.beneficaryName = beneficaryName;
	}



	public LocalDate getDateAdded() {
		return dateAdded;
	}



	public void setDateAdded(LocalDate dateAdded) {
		this.dateAdded = dateAdded;
	}


	
	
	
}
