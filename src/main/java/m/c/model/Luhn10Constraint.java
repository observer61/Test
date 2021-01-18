package m.c.model;

import m.c.model.validator.CreditCardLuhn10Validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CreditCardLuhn10Validator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Luhn10Constraint {
    String message() default "Failed Luhn 10 check.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
