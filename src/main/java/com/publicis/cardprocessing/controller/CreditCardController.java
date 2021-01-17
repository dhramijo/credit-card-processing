package com.publicis.cardprocessing.controller;

import com.publicis.cardprocessing.model.CreditCard;
import com.publicis.cardprocessing.service.CreditCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * Handles web requests related to credit card processing.
 */
@RestController
@RequestMapping("/api/v1")
@Api(value="Rest API for a credit card provider.")
class CreditCardController {

    private CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    /**
     * Create a new credit card in the system.
     * @param creditCard A new credit card to add to the system.
     * @return response that the new credit card was added to the system
     *
     */
    @PostMapping("/card")
    @ApiOperation(value = "Create a new credit card account in the system.")
    public ResponseEntity<?> createNewCreditCard(@Valid @RequestBody CreditCard creditCard) {
        creditCardService.saveNewCreditCard(creditCard);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(creditCard.getId()).toUri();
        return ResponseEntity.created(location).body(creditCard);
    }


    /**
     * Returns the information of all the credit cards available
     */
    @GetMapping("/cards")
    @ApiOperation(value = "Get all the available credit cards in the system", response = ResponseEntity.class)
    public ResponseEntity<?> getAllCustomers(){
        return ResponseEntity.ok().body(creditCardService.getAllCreditCards());
    }

}
