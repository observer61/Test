package m.c.model.validator;

import m.c.model.Luhn10Constraint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;

public class CreditCardLuhn10Validator implements ConstraintValidator<Luhn10Constraint, @NotNull String> {

    private final Logger logger = LoggerFactory.getLogger(CreditCardLuhn10Validator.class);

    @Override
    public boolean isValid(String creditCardNumber, ConstraintValidatorContext constraintValidatorContext) {
        logger.info("Validating credit card number: " + creditCardNumber);

        if (creditCardNumber == null || creditCardNumber.isEmpty()) {
            logger.warn("creditCardNumber was empty");
            return false;
        }

        try {
            return isLuhn10(creditCardNumber, getDigits(creditCardNumber));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isLuhn10(String creditCardNumber, int[] ints) {
        if (getLuhnSum(ints) % 10 == 0) {
            logger.info(creditCardNumber + " is a valid credit card number");
            return true;
        } else {
            logger.info(creditCardNumber + " is an invalid credit card number");
            return false;
        }
    }

    private int getLuhnSum(int[] ints) {
        doubleEveryOtherDigit(ints);
        return getSum(ints);
    }

    private void doubleEveryOtherDigit(int[] ints) {
        for (int i = ints.length - 2; i >= 0; i = i - 2) {
            int digitToDouble = ints[i];
            digitToDouble = digitToDouble * 2;
            if (digitToDouble > 9) {
                digitToDouble = digitToDouble % 10 + 1;
            }
            ints[i] = digitToDouble;
        }
    }

    private int getSum(int[] ints) {
        int sum = 0;
        for (int i : ints) {
            sum += i;
        }
        return sum;
    }

    private int[] getDigits(String creditCardNumber) throws NumberFormatException {
        int[] ints = new int[creditCardNumber.length()];
        for (int i = 0; i < creditCardNumber.length(); i++) {
            ints[i] = Integer.parseInt(creditCardNumber.substring(i, i + 1));
        }
        return ints;
    }

}
