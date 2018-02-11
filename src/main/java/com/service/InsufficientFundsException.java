package com.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Creditor does not have sufficient funds for transaction to comply")
class InsufficientFundsException extends RuntimeException {
}
