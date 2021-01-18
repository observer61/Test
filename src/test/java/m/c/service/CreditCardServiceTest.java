package m.c.service;

import m.c.model.CreditCard;
import m.c.repository.CreditCardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class CreditCardServiceTest {

    @InjectMocks
    private CreditCardService creditCardService;
    @Mock
    CreditCardRepository creditCardRepositoryMock;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testGetAllCards_ReturnsCreditCardsFromRepositoryFindAll() {
        Iterable<CreditCard> creditCards = Arrays.asList(new CreditCard(), new CreditCard());
        when(creditCardRepositoryMock.findAll()).thenReturn(creditCards);

        Iterable<CreditCard> allCards = creditCardService.getAllCards();

        assertEquals(creditCards, allCards);
    }

    @Test
    void testAdd_PassesCreditCardToRepositorySave() throws Exception {
        CreditCard creditCard = new CreditCard();

        creditCardService.add(creditCard);

        verify(creditCardRepositoryMock).save(creditCard);
    }

    @Test
    void testAdd_ThrowsDuplicateCreditCardNumberExceptionWhenSaveThrowsDataIntegrityViolationException() {
        CreditCard creditCard = new CreditCard();

        when(creditCardRepositoryMock.save(creditCard)).thenThrow(new DataIntegrityViolationException(""));

        assertThrows(DuplicateCreditCardNumberException.class, () -> creditCardService.add(creditCard));
    }

}