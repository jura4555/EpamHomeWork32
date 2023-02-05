package com.epam.spring.travel_agency.controller.dto.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DescriptionValidator implements ConstraintValidator<ValidDescription, String> {
    @Override
    public void initialize(ValidDescription constraintAnnotation) {
    }

    @Override
    public boolean isValid(String description, ConstraintValidatorContext constraintValidatorContext) {
        return description.split(" ").length > 3;
    }
}
