package com.banking.leaningbank.model;

import java.util.List;

public class CustomerModel {
	
	
	private Integer customerId;
	private String customerName;
	private Account accountDetails;
	private String email;
	private List<Beneficiary> beneficiaryList;
	
	
	public CustomerModel(Integer customerId, String customerName,Account accountDetails , String email,
			List<Beneficiary> beneficiaryList) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.accountDetails = accountDetails;
		this.email = email;
		this.beneficiaryList = beneficiaryList;
	}
	
	
	public CustomerModel() {};
	
	

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	

	public Account getAccountDetails() {
		return accountDetails;
	}


	public void setAccountDetails(Account accountDetails) {
		this.accountDetails = accountDetails;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Beneficiary> getBeneficaryList() {
		return beneficiaryList;
	}

	public void setBeneficaryList(List<Beneficiary> beneficaryList) {
		this.beneficiaryList = beneficaryList;
	}
}

