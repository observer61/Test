package m.c.controller;

import m.c.model.CreditCard;
import m.c.service.CreditCardService;
import m.c.service.DuplicateCreditCardNumberException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

class CreditCardControllerTest {

    @InjectMocks
    private CreditCardController creditCardController;
    @Mock
    private CreditCardService creditCardServiceMock;
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
    void testGetAll_ReturnsCreditCardsFromServiceGetAllCards() {
        Iterable<CreditCard> creditCards = Arrays.asList(new CreditCard(), new CreditCard());
        when(creditCardServiceMock.getAllCards()).thenReturn(creditCards);

        ResponseEntity<Object> responseEntity = creditCardController.getAll();

        assertEquals(responseEntity.getBody(), creditCards);
    }

    @Test
    void testGetAll_ReturnsOkStatusCode() {
        Iterable<CreditCard> creditCards = Arrays.asList(new CreditCard(), new CreditCard());
        when(creditCardServiceMock.getAllCards()).thenReturn(creditCards);

        ResponseEntity<Object> responseEntity = creditCardController.getAll();

        assertEquals(responseEntity.getStatusCodeValue(), OK.value());
    }

    @Test
    void testCreate_PassesCreditCardToServiceAdd() throws Exception{
        CreditCard creditCard = new CreditCard();

        creditCardController.create(creditCard);

        verify(creditCardServiceMock).add(creditCard);
    }

    @Test
    void testCreate_ReturnsCreatedStatusCode() throws Exception {
        CreditCard creditCard = new CreditCard();

        ResponseEntity<Object> responseEntity = creditCardController.create(creditCard);

        assertEquals(responseEntity.getStatusCodeValue(), CREATED.value());
    }
}