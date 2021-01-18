package m.c.controller;

import m.c.model.CreditCard;
import m.c.service.CreditCardService;
import m.c.service.DuplicateCreditCardNumberException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/credit-cards")
public class CreditCardController {

    private final Logger logger = LoggerFactory.getLogger(CreditCardController.class);

    @Autowired
    CreditCardService creditCardService;

    @GetMapping(name = "/", produces = "application/json")
    public ResponseEntity<Object> getAll() {
        logger.info("Received Request to get all Credit Cards");
        return ResponseEntity.status(HttpStatus.OK).body(creditCardService.getAllCards());
    }

    @PostMapping(name = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> create(@RequestBody @Valid CreditCard creditCard) throws DuplicateCreditCardNumberException {
        logger.info("Received Request to create new Credit Card: " + creditCard);
        creditCardService.add(creditCard);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
