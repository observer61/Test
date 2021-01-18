package m.c.model.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CreditCardLuhn10ValidatorTest {

    private CreditCardLuhn10Validator creditCardLuhn10Validator;

    @BeforeEach
    void setUp() {
        creditCardLuhn10Validator = new CreditCardLuhn10Validator();
    }

    @Test
    void testIsValid_ReturnsFalseWhenCreditDardNumberIsNull() {
        boolean validationResult = creditCardLuhn10Validator.isValid(null, null);
        
        assertFalse(validationResult);
    }

    @Test
    void testIsValid_ReturnsFalseWhenCreditDardNumberIsEmpty() {
        boolean validationResult = creditCardLuhn10Validator.isValid("", null);

        assertFalse(validationResult);
    }

    @Test
    void testIsValid_ReturnsFalseWhenCreditDardNumberHasNonNumberChar() {
        boolean validationResult = creditCardLuhn10Validator.isValid("s", null);

        assertFalse(validationResult);
    }
    
    @Test
    void name6() {
        boolean validationResult = creditCardLuhn10Validator.isValid("44", null);

        assertFalse(validationResult);
    }
    
    @Test
    void name5() {
        boolean validationResult = creditCardLuhn10Validator.isValid("5249", null);

        assertTrue(validationResult);
    }
}