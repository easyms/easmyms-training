package com.easyms.training.sampleapp.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = EmailExistingValidator.class)
public @interface EmailExisting {

    String message() default "{EmailExisting}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
