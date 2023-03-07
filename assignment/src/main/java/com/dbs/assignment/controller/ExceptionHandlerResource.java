package com.dbs.assignment.controller;

import com.dbs.assignment.exception.FileNotValidException;
import com.dbs.assignment.exception.MarginNotFoundException;
import com.dbs.assignment.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice

public class ExceptionHandlerResource {

    @ExceptionHandler(MarginNotFoundException.class)
    ResponseEntity<ErrorResponse> handleMarginsNotFoundException(MarginNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileNotValidException.class)
    ResponseEntity<ErrorResponse> handleFileNotValidException(FileNotValidException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponse> handleGenericException(Exception e)  {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
