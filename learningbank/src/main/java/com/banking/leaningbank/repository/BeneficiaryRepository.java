package com.banking.leaningbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.leaningbank.model.Beneficiary;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer> {

	List<Beneficiary> findByCustomerId(Integer customerId);

	Beneficiary findByBeneficaryIdAndCustomerId(Integer beneficiaryId, Integer customerId);

	
}
