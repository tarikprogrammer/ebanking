package com.botola.clientapi.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class ValidationExceptionObject extends RuntimeException {
    private Map<?,?> validationErrors;

}
