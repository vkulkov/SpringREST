package com.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Transaction")
class TransferNotFoundException extends RuntimeException {
    TransferNotFoundException(String message) {
        super(message);
    }
}
