package com.footballstatistics.backend.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such identifier")
public class InvalidIdentifier extends RuntimeException{
}
