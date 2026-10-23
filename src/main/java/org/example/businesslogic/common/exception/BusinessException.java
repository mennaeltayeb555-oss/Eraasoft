package org.example.businesslogic.common.exception;

// Thrown when a request breaks one of the business rules
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
