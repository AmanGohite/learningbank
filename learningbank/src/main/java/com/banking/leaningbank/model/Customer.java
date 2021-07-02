package com.banking.leaningbank.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;



@Entity
@Table(name="customers")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cust_id")
	private Integer customerId;
	
	@Column(name="cust_name")
	@Size(message="Customer name should be between 2 to 25 chars")
	@NotBlank(message="name cannot be blank")
	private String customerName;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@Transient
	private Double initialBalance;
	
	
	@Email(message="email should be in proper format")
	private String email;
	
	

	
	
	

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	

	public Double getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(Double initialBalance) {
		this.initialBalance = initialBalance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
