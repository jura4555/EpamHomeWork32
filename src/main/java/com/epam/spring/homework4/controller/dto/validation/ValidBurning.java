package com.epam.spring.homework4.controller.dto.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BurningValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBurning {
    String message() default "Invalid burning";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
