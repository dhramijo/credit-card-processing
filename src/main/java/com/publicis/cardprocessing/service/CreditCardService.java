package com.publicis.cardprocessing.service;

import com.publicis.cardprocessing.exception.CreditCardException;
import com.publicis.cardprocessing.model.CreditCard;
import com.publicis.cardprocessing.repository.CreditCardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CreditCardService {

    private CreditCardRepository creditCardRepository;

    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public CreditCard saveNewCreditCard(CreditCard creditCard) {
        if(!isValidCreditCardNumber(creditCard.getCardNumber()))
            throw new CreditCardException();
        return creditCardRepository.save(creditCard);
    }

    public List<CreditCard> getAllCreditCards() {
        return creditCardRepository.findAll();
    }

    private boolean isValidCreditCardNumber(String ccNumber) {
        if (ccNumber.length() > 19)
            return false;

        int totalSum = 0;
        boolean isAlternate = false;
        for (int i = ccNumber.length() - 1; i >= 0; i--) {
            int number = Integer.parseInt(ccNumber.substring(i, i + 1));
            if (isAlternate) {
                number *= 2;
                if (number > 9) {
                    number = (number % 10) + 1;
                }
            }
            totalSum += number;
            isAlternate = !isAlternate;
        }
        return (totalSum % 10 == 0);
    }
}
