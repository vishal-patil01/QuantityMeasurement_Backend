package com.quantitymeasurement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class QuantityMeasurementExceptionHandler {
    @ExceptionHandler(QuantityMeasurementException.class)
    public ResponseEntity<String> quantityMeasurementExceptionHandler(QuantityMeasurementException e) {
        return new ResponseEntity<>(e.message, HttpStatus.BAD_REQUEST);
    }
}
