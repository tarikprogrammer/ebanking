package com.botola.clientapi.handler;



import com.botola.clientapi.Exceptions.ValidationExceptionObject;
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
