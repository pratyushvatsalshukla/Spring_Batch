package com.redisDB.learningRedis.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class CreditCardTransactions {
    private String cardTypeCode ;
    private String cardTypeFullName ;
    private String issuingBank ;
    @Id
    private String cardNumber ;
    private String cardHolderName ;
    private String cvvCvv2 ;
    private String issueDate ;
    private String expiryDate ;
    private String cardLimit ;
}
