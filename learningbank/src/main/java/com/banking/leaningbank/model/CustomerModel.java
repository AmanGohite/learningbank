package com.banking.leaningbank.model;

import java.util.List;

public class CustomerModel {
	
	
	private Integer customerId;
	private String customerName;
	private Double accountBalance;
	private String accountNo;
	private String email;
	private List<Beneficiary> beneficiaryList;
	
	
	public CustomerModel(Integer customerId, String customerName, Double accountBalance, String email,String accountNo,
			List<Beneficiary> beneficiaryList) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.accountBalance = accountBalance;
		this.email = email;
		this.accountNo = accountNo;
		this.beneficiaryList = beneficiaryList;
	}
	
	
	public CustomerModel() {};
	
	

	public String getAccountNo() {
		return accountNo;
	}


	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}


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



	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
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

