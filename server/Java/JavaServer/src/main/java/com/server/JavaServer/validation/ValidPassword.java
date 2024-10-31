package com.server.JavaServer.validation;

import jakarta.validation.*;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
 String message() default "Invalid password format";
 Class<?>[] groups() default {};
 Class<? extends Payload>[] payload() default {};
}
