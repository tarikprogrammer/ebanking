package com.botola.agentapi.Handler;


import com.botola.agentapi.Exceptions.ValidationExceptionObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> entityNotFoundException(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValidationExceptionObject.class)
    public ResponseEntity<?> handlerValidationException(ValidationExceptionObject e){
        return ResponseEntity.badRequest().body(e.getValidationErrors());
    }



}
