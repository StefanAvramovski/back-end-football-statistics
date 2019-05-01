package com.footballstatistics.backend.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason= "Bad Identifier")
public class BadIdentifier extends RuntimeException {
}
