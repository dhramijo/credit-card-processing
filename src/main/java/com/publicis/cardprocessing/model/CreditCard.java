package com.publicis.cardprocessing.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Data
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Size(max = 19, message = "Credit card number must be less or equal 19")
    @Pattern(regexp = "[0-9]+", message = "Credit card number must be numeric")
    private String cardNumber;

    @JsonProperty(value = "balance")
    private BigDecimal cardBalance;

    @JsonProperty(value = "limit")
    private BigDecimal cardLimit;
}
