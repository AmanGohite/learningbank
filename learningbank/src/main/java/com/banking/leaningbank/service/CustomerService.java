package com.banking.leaningbank.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.type.LocalDateType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.leaningbank.exception.BeneficiaryNotFoundException;
import com.banking.leaningbank.exception.InsufficientBalanceException;
import com.banking.leaningbank.model.Beneficiary;
import com.banking.leaningbank.model.Customer;
import com.banking.leaningbank.model.CustomerLogin;
import com.banking.leaningbank.model.CustomerModel;
import com.banking.leaningbank.model.FundTransfer;
import com.banking.leaningbank.model.TransferRequest;
import com.banking.leaningbank.repository.BeneficiaryRepository;
import com.banking.leaningbank.repository.CustomerRepository;
import com.google.common.base.Optional;

@Service
public class CustomerService {	

	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	CustomerRepository custRepo;
	
	@Autowired
	BeneficiaryRepository benfRepo;
	
	
	public Boolean addCustomer(Customer customer) {
		boolean flag=false;
		try {
			
			Customer cust =custRepo.save(customer);
			List<Beneficiary> beneficiaryList = new ArrayList();
			beneficiaryList.add(new Beneficiary( cust.getCustomerId(), "Beneficiary1", LocalDate.now()));
			beneficiaryList.add(new Beneficiary( cust.getCustomerId(), "Beneficiary2", LocalDate.now()));
			
			beneficiaryList.forEach(b->benfRepo.save(b));
			
			flag=true;
			return flag;
		}catch (Exception e) {
			logger.error("error adding customer");
		}
		return flag;
	}


	public CustomerModel loginCustomer(CustomerLogin customer) {
		CustomerModel custMod = new CustomerModel();
		try {
			
			Customer cust = custRepo.findByEmailAndPassword(customer.getEmail(),customer.getPassword());
				if (cust != null) {
					 custMod = new CustomerModel(cust.getCustomerId(), cust.getCustomerName(),
							cust.getAccountBalance(), cust.getEmail(), this.getBeneficiary(cust.getCustomerId()));

					return custMod;
				}

		}catch (Exception e) {
			logger.error("error retreiving Customer with email {}",customer.getEmail());
		}
		return custMod;
	}


	private List<Beneficiary> getBeneficiary(Integer customerId) {
		List<Beneficiary> benelist  = new ArrayList();
		try{
			benelist =  benfRepo.findByCustomerId(customerId);
			return benelist;
		}catch (Exception e) {
			logger.info("error retreiving beneficiary list");
		}
		return benelist;
	}


	public FundTransfer fundTransfer(TransferRequest transfer) {
		boolean flag = this.checkBeneficiary(transfer.getCustomerId(), transfer.getBeneficiaryId());
		if(flag) {
			Double balance = this.getAccountBalance(transfer.getCustomerId());
			if(balance < transfer.getAmount()) {
				throw new InsufficientBalanceException("inSufficient Balance");
			}
			Double updatedAmount =  this.fundTransferFromAccount(balance,transfer.getAmount());
			//if(transferFlag) {
				return new FundTransfer(transfer.getCustomerId(),transfer.getBeneficiaryName(),
						transfer.getBeneficiaryId(),transfer.getAmount(),updatedAmount,"Successfully transfered to beneficiary",
						 LocalDate.now());
						
			//}
		}
		else {
			throw new BeneficiaryNotFoundException("beneficiary not found for this customer");
		}
		
	}
	
	private synchronized Double fundTransferFromAccount(Double balance, Double transactionAmount) {
		Double updatedBalance = (balance - transactionAmount);
		Customer customer = new Customer();
		
		try {
		customer.setAccountBalance(updatedBalance);
		Customer updatedCust = custRepo.save(customer);
		return updatedCust.getAccountBalance();
		} catch (Exception e) {
			logger.info("error updating balance");
		}
		return customer.getAccountBalance();
	}


	private boolean checkBeneficiary(Integer customerId, Integer beneficiaryId) {
		boolean isValidTransaction = false;
		try {
			Beneficiary beneficiary = benfRepo.findByBeneficaryIdAndCustomerId(beneficiaryId, customerId);
			if(beneficiary ==null) {
				throw new BeneficiaryNotFoundException("cannot find this beneficiary for this user");
			}
			return true;
		}catch (Exception e) {
			logger.error("error validating beneficiary");
		}
			return isValidTransaction;
	}
	
	

	private Double getAccountBalance(Integer customerId) {
		try {
			java.util.Optional<Customer> customer = custRepo.findById(customerId);
			if(customer.isPresent()) {
				return	customer.get().getAccountBalance();
			}	 
		}catch (Exception e) {
			logger.error("error getting account balance");
		}
		return null;
	}
		
}
