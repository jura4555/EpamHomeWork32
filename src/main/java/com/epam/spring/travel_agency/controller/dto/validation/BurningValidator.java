package com.epam.spring.travel_agency.controller.dto.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BurningValidator implements ConstraintValidator<ValidBurning, Boolean> {

    @Override
    public void initialize(ValidBurning constraintAnnotation) {
    }

    @Override
    public boolean isValid(Boolean b, ConstraintValidatorContext constraintValidatorContext) {
        return !b;
    }
}
