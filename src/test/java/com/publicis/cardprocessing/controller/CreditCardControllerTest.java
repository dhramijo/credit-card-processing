package com.publicis.cardprocessing.controller;

import com.publicis.cardprocessing.exception.CreditCardException;
import com.publicis.cardprocessing.model.CreditCard;
import com.publicis.cardprocessing.service.CreditCardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Implements testing of the {@CreditCardController} class.
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class CreditCardControllerTest {

    @Autowired private MockMvc mvc;

    @Autowired private JacksonTester<CreditCard> json;

    @MockBean  private CreditCardService creditCardService;

    @Test
    void testCreateNewCreditCard() throws Exception {
        CreditCard creditCard = getCreditCard();
        mvc.perform(
                post(new URI("/api/v1/card"))
                        .content(json.write(creditCard).getJson())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(creditCard.getName()))
                .andExpect(jsonPath("$.cardNumber").value(creditCard.getCardNumber()))
                .andExpect(jsonPath("$.limit").value(creditCard.getCardLimit()))
                .andExpect(jsonPath("$.balance").value(creditCard.getCardBalance()));

        verify(creditCardService, times(1)).saveNewCreditCard(creditCard);
    }


    @Test
    void testNotValidCreditCardNumber() throws Exception {

        CreditCard creditCard = getCreditCard();
        creditCard.setCardNumber("1111222233334444555");

        when(creditCardService.saveNewCreditCard(creditCard)).thenThrow(new CreditCardException());

        mvc.perform(
                post(new URI("/api/v1/card"))
                        .content(json.write(creditCard).getJson())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    void getAllCreditCards() throws Exception {

        List<CreditCard> creditCards = new ArrayList<>();
        creditCards.add(getCreditCard());

        when(creditCardService.getAllCreditCards()).thenReturn(creditCards);

        mvc.perform(
                get(new URI("/api/v1/cards"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Joni")))
                .andExpect(jsonPath("$[0].cardNumber", is("1111222233334444")))
                .andExpect(jsonPath("$[0].limit", is(5000)))
                .andExpect(jsonPath("$[0].balance", is(1000)));

        verify(creditCardService, times(1)).getAllCreditCards();
    }


    private CreditCard getCreditCard() {
        CreditCard creditCard = new CreditCard();
        creditCard.setId(1l);
        creditCard.setName("Joni");
        creditCard.setCardNumber("1111222233334444");
        creditCard.setCardBalance(new BigDecimal("1000"));
        creditCard.setCardLimit(new BigDecimal("5000"));
        return creditCard;
    }
}