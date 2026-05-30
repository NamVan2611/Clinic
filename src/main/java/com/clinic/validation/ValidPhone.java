package com.clinic.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValidator.class)
@Documented
public @interface ValidPhone {
    String message() default "Phone number must be valid (10-20 digits with optional + or -)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
