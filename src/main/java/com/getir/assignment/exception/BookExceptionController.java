package com.getir.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class BookExceptionController {

    @ExceptionHandler(value = Exception.class)
    public ErrorResponse handleException(Exception exception){
        return new ErrorResponse();
    }

    @ExceptionHandler(value = CustomerValidationException.class)
    public ErrorResponse handleCustomerException(CustomerValidationException customerValidationException){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return new ErrorResponse(httpStatus,customerValidationException.getMessage());
    }

}
