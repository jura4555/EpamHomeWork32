package com.epam.spring.homework4.controller.dto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DescriptionValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDescription {
    String message() default "Invalid description";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
