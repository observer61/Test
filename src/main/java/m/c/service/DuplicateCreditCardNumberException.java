package m.c.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class DuplicateCreditCardNumberException extends Exception {

    public DuplicateCreditCardNumberException(String message) {
        super(message);
    }

}
