package com.banking.leaningbank.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.hibernate.type.LocalDateType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.leaningbank.exception.BeneficiaryNotFoundException;
import com.banking.leaningbank.exception.InsufficientBalanceException;
import com.banking.leaningbank.model.Account;
import com.banking.leaningbank.model.Beneficiary;
import com.banking.leaningbank.model.Customer;
import com.banking.leaningbank.model.CustomerLogin;
import com.banking.leaningbank.model.CustomerModel;
import com.banking.leaningbank.model.FundTransfer;
import com.banking.leaningbank.model.Transaction;
import com.banking.leaningbank.model.TransferRequest;
import com.banking.leaningbank.repository.AccountRepository;
import com.banking.leaningbank.repository.BeneficiaryRepository;
import com.banking.leaningbank.repository.CustomerRepository;
import com.banking.leaningbank.repository.TransactionRepository;
import com.google.common.base.Optional;

@Service
public class CustomerService {	

	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	CustomerRepository custRepo;
	
	@Autowired
	BeneficiaryRepository benfRepo;
	
	@Autowired
	TransactionRepository tranRepo;
	
	@Autowired
	AccountRepository accountRepo;
	
	public String addCustomer(Customer customer) {
		String flag="";
		try {
				Customer isvalidCust = custRepo.findByEmail(customer.getEmail());
				if(isvalidCust == null) {
				customer.setPassword(generateString());
			
				Customer cust =custRepo.save(customer);
				List<Beneficiary> beneficiaryList = new ArrayList();
				beneficiaryList.add(new Beneficiary( cust.getCustomerId(), "Beneficiary1", LocalDate.now()));
				beneficiaryList.add(new Beneficiary( cust.getCustomerId(), "Beneficiary2", LocalDate.now()));
				
				beneficiaryList.forEach(b->benfRepo.save(b));
				accountRepo.save(new Account(cust.getCustomerId(),
						"DEMOBANK000"+cust.getCustomerId()
						, customer.getInitialBalance()));

				flag=cust.getPassword();
				return flag;
				}
			
		}catch (Exception e) {
			logger.error("error adding customer {}",e.getMessage());
		}
		return flag;
	}


	public CustomerModel loginCustomer(CustomerLogin customer) {
		CustomerModel custMod = new CustomerModel();
		try {
			
			Customer cust = custRepo.findByEmailAndPassword(customer.getEmail(),customer.getPassword());
				if (cust != null) {
					Account account = accountRepo.findByCustomerId(cust.getCustomerId());
					
					 custMod = new CustomerModel(cust.getCustomerId(), cust.getCustomerName(),
							account,cust.getEmail() ,this.getBeneficiary(cust.getCustomerId()));

					return custMod;
				}

		}catch (Exception e) {
			logger.error("error retreiving Customer with email {}",e.getMessage());
		}
		return null;
	}


	private List<Beneficiary> getBeneficiary(Integer customerId) {
		List<Beneficiary> benelist  = new ArrayList();
		try{
			benelist =  benfRepo.findByCustomerId(customerId);
			return benelist;
		}catch (Exception e) {
			logger.info("error retreiving beneficiary list {}",e.getMessage());
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
			Double updatedAmount =  this.fundTransferFromAccount(balance,transfer.getAmount(),transfer.getCustomerId());
			if(updatedAmount != null) {
				
				tranRepo.save(new Transaction(transfer.getAmount(), transfer.getCustomerId(), transfer.getBeneficiaryId()));
				
				return new FundTransfer(transfer.getCustomerId(),transfer.getBeneficiaryName(),
						transfer.getBeneficiaryId(),transfer.getAmount(),updatedAmount,"Successfully transfered to beneficiary",
						 LocalDate.now());
						
			}
		}
		else {
			throw new BeneficiaryNotFoundException("beneficiary not found for this customer");
		}
		return new FundTransfer(transfer.getCustomerId(),transfer.getBeneficiaryName(),
				transfer.getBeneficiaryId(),transfer.getAmount(),00.0,"error occured while transfer",
				 LocalDate.now());
	}
	
	private synchronized Double fundTransferFromAccount(Double balance, Double transactionAmount, Integer custId) {
		Double updatedBalance = (balance - transactionAmount);
		
		try {
		Account a = accountRepo.findByCustomerId(custId);
		if(a != null) {
			
			a.setAccountBalance(updatedBalance);
			accountRepo.save(a);
			}	 
		
		return updatedBalance;
		} catch (Exception e) {
			logger.error("error updating balance {}",e.getMessage());
		}
		return null;
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
			logger.error("error validating beneficiary {}",e.getMessage());
		}
			return isValidTransaction;
	}
	
	

	private Double getAccountBalance(Integer customerId) {
		try {
			Account account = accountRepo.findByCustomerId(customerId);
			if(account != null) {
				return	account.getAccountBalance();
			}	 
		}catch (Exception e) {
			logger.error("error getting account balance {}",e.getMessage());
		}
		return null;
	}


	public boolean deleteBeneficiary(Integer beneficiaryId) {
		boolean flag=false;
		try {
			benfRepo.deleteById(beneficiaryId);
			flag=true;
			return flag;
		}catch (Exception e) {
			logger.error("error deleting beneficiary {}",e.getMessage());
		}
	return flag;
	}


	public boolean updateBeneficiary(Beneficiary beneficiary) {
		boolean flag=false;
		try {
			benfRepo.save(beneficiary);
			flag=true;
			return flag;
		}catch (Exception e) {
			logger.error("error modifying beneficiary {}",e.getMessage());
		}
	return flag;
	}
	
	  public static String generateString() {
	        String password = UUID.randomUUID().toString();
	        return password.replace("-", "").substring(0, 10);
	    }
		
}
