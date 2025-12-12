package com.example.nobs.exception;

public enum ErrorMessages {
    PRODUCT_NOT_FOUND("Product Not Found"),
    NAME_REQUIRED("Product name isrequired"),
    DESCRIPTION_REQUIRED("Product description must be more than 20 characters"),
    PRICE_CANNOT_BE_NEGATIVE("Product price cannot be negative");
    private final String message;

    ErrorMessages(String message) {

        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
