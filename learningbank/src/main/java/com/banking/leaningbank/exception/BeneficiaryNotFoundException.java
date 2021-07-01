package com.banking.leaningbank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BeneficiaryNotFoundException extends RuntimeException {

  public BeneficiaryNotFoundException(String exception) {
    super(exception);
  }
}
