package com.banking.leaningbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.leaningbank.model.Transaction;


public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
