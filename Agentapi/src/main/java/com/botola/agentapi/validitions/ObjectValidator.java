package com.botola.agentapi.validitions;



import com.botola.agentapi.Exceptions.ValidationExceptionObject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectValidator<T> {
    private final ValidatorFactory mValidatorFactory = Validation.buildDefaultValidatorFactory();
    private Validator mValidator = mValidatorFactory.getValidator();

    public void validate(T object) {
        Set<ConstraintViolation<T>> constraintViolations = mValidator.validate(object);
        if (!constraintViolations.isEmpty()) {
            Map<String, String> jsonErrors = constraintViolations.stream()
                    .collect(Collectors.toMap(
                            violation -> violation.getPropertyPath().toString(),
                            ConstraintViolation::getMessage,
                            (existing, replacement) -> existing
                    ));
            throw new ValidationExceptionObject(Map.of("errors", jsonErrors));
        }
    }

}
