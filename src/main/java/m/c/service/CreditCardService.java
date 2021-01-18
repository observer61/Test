package m.c.service;

import m.c.model.CreditCard;
import m.c.repository.CreditCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService {

    private final Logger logger = LoggerFactory.getLogger(CreditCardService.class);

    @Autowired
    CreditCardRepository creditCardRepository;

    public void add(CreditCard creditCard) throws DuplicateCreditCardNumberException {
        try {
            creditCardRepository.save(creditCard);
        } catch (DataIntegrityViolationException e) {
            logger.warn("Attempted to insert existing credit card: " + creditCard.getCardNumber());
            throw new DuplicateCreditCardNumberException(e.getMessage());
        }
    }

    public Iterable<CreditCard> getAllCards() {
        Iterable<CreditCard> all = creditCardRepository.findAll();
        all.forEach(a -> logger.info("Found Credit Card: " + a.toString()));
        return all;
    }

}
