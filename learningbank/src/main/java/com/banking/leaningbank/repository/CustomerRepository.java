package com.banking.leaningbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.leaningbank.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer findByEmailAndPassword(String email, String password);

	Customer findByEmail(String email);

}
