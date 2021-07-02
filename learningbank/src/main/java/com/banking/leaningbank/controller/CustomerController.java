package com.banking.leaningbank.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.util.UriComponentsBuilder;

import com.banking.leaningbank.exception.CustomerNotFoundException;
import com.banking.leaningbank.model.Beneficiary;
import com.banking.leaningbank.model.Customer;
import com.banking.leaningbank.model.CustomerLogin;
import com.banking.leaningbank.model.CustomerModel;
import com.banking.leaningbank.model.FundTransfer;
import com.banking.leaningbank.model.TransferRequest;
import com.banking.leaningbank.service.CustomerService;
import com.google.gson.Gson;

@RestController
@RequestMapping(value="/customer")
public class CustomerController {

	@Autowired
	CustomerService custService;

	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());



	@PostMapping(value="/")
	public ResponseEntity<Map<String,String>> addCustomer(@RequestBody @Valid Customer customer, UriComponentsBuilder builder) {
		String json = new Gson().toJson(customer);
		logger.info("request received for adding customer {}", json);
		String flag = custService.addCustomer(customer);
		Map<String, String> responseText = new HashMap();
		if (StringUtils.isEmpty(flag)) {
			responseText.put("response", "error occured while adding new customer");
			return new ResponseEntity<Map<String,String>>(responseText,HttpStatus.CONFLICT);
		}
		responseText.put("response", "Customer suggesfully registered, Please proceed to login with email and password as "+flag);
		logger.info("request end for adding customer {}", json);
		return new ResponseEntity<Map<String,String>>(responseText, HttpStatus.CREATED);

	}
	
	
	
	@PostMapping(value="/login")
	public ResponseEntity<CustomerModel> loginCustomer(@RequestBody  CustomerLogin customer) {
		String json = new Gson().toJson(customer);
		logger.info("request received for Login customer {}", json);
		CustomerModel Respcustomer = custService.loginCustomer(customer);
		if (Respcustomer == null) {
			throw new CustomerNotFoundException("User id or password is incorrect");
		}
		logger.info("request end for Login customer {}", json);
		return new ResponseEntity<CustomerModel>(Respcustomer, HttpStatus.OK);

	}

	
	@PostMapping(value="/transfer")
	public ResponseEntity<FundTransfer> fundTransfer(@RequestBody TransferRequest transfer){
		String json = new Gson().toJson(transfer);
		logger.info("request received for fund transfer by customer {}", json);
		FundTransfer fund = custService.fundTransfer(transfer);
		return new ResponseEntity<FundTransfer>(fund,HttpStatus.OK);
		
	}
	
	@DeleteMapping(value="/{beneficiary}")
	public ResponseEntity<Void> deleteBeneficiary(@PathVariable("beneficiary") Integer beneficiaryId){
		boolean flag = custService.deleteBeneficiary(beneficiaryId);
		if(flag) {
		return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@PostMapping(value="/beneficiary")
	public ResponseEntity<Void> updateBeneficiary(Beneficiary beneficiary){
		beneficiary.setDateAdded(LocalDate.now());
		boolean flag = custService.updateBeneficiary(beneficiary);
		if(flag) {
			return new ResponseEntity<Void>( HttpStatus.OK);

		}
		return new ResponseEntity<Void>( HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	
}
