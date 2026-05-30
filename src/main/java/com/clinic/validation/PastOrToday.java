package com.clinic.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PastOrTodayValidator.class)
@Documented
public @interface PastOrToday {
    String message() default "Date must be in the past or today";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
