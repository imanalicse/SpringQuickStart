package com.imanali.SpringQuickStart.exception;

import org.springframework.http.HttpStatus;

// record class - private final, getter, public constructor, equals, hashcode and to string
public record ApiException(String message, Throwable throwable, HttpStatus httpStatus) {

}
