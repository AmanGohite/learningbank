package com.banking.leaningbank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="account_id")
	private Integer accountId;
	
	@Column(name="cust_id")
	private Integer customerId;
	
	@Column(name="account_no")
	private String accountNo;
	
	@Column(name="account_bal")
	private Double accountBalance;
	
	public Account() {}

	public Account( Integer customerId, String accountNo, Double accountBalance) {
		super();
		this.customerId = customerId;
		this.accountNo = accountNo;
		this.accountBalance = accountBalance;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	

}
