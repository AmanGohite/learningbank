package com.banking.leaningbank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="transaction_id")
	private Integer transactionId;
	
	private Double amount;
	
	
	private Integer custId;
	
	private Integer beneficiary;
	
	public Transaction() {};

	public Transaction(Double amount ,Integer custId, Integer beneficiary) {
		super();
		this.amount = amount;
		this.custId = custId;
		this.beneficiary = beneficiary;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}



	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public Integer getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(Integer beneficiary) {
		this.beneficiary = beneficiary;
	}
	
	

}
