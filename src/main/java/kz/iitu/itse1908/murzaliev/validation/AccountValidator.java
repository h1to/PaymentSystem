package kz.iitu.itse1908.murzaliev.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountValidator implements ConstraintValidator<AccountConstraintValidator, String> {

    @Override
    public void initialize(AccountConstraintValidator constraintAnnotation) {
    }

    @AssertTrue
    @Override
    public boolean isValid(String account, ConstraintValidatorContext constraintValidatorContext) {
        return account != null && !account.contains(" ");
    }
}
