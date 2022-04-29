package kz.iitu.itse1908.murzaliev.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AccountValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AccountConstraintValidator {
    String message() default "Invalid account";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
