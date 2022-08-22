package com.epam.spring.homework4.controller.dto.validation;

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
