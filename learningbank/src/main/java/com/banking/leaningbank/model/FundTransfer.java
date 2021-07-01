package com.banking.leaningbank.model;

import java.time.LocalDate;

public class FundTransfer {

	
	private Integer customerId;
	
	private String beneficiaryName;
	
	private Integer beneficiaryId;

	private Double amount;
	
	private Double updatedBalance;
	
	private String message;
	
	private LocalDate dateTime;
	
	
	public FundTransfer() {}
	
	
	
	
	public FundTransfer(Integer customerId, String beneficiaryName, Integer beneficiaryId, Double amount,
			Double updatedBalance, String message, LocalDate dateTime) {
		super();
		this.customerId = customerId;
		this.beneficiaryName = beneficiaryName;
		this.beneficiaryId = beneficiaryId;
		this.amount = amount;
		this.updatedBalance = updatedBalance;
		this.message = message;
		this.dateTime = dateTime;
	}

	public LocalDate getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDate dateTime) {
		this.dateTime = dateTime;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public Integer getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(Integer beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getUpdatedBalance() {
		return updatedBalance;
	}

	public void setUpdatedBalance(Double updatedBalance) {
		this.updatedBalance = updatedBalance;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
