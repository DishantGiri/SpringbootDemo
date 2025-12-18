package com.example.nobs.exception;

import com.example.nobs.product.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionalHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionalHandler.class);

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleProductNotFoundException(ProductNotFoundException exception){
        return new ErrorResponse(exception.getMessage());
    }
    @ExceptionHandler(ProductNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleProductNotValidException(ProductNotValidException exception){
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleAllExceptions(Exception exception){
        log.error("Unhandled exception occurred", exception);
        return new ErrorResponse("Internal server error: " + exception.getMessage());
    }
}
