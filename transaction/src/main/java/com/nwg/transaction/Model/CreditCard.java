package com.nwg.transaction.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {
    private String cardTypeCode ;
    private String cardTypeFullName ;
    private String issuingBank ;
    private String cardNumber ;
    private String cardHolderName ;
    private String cvvCvv2 ;
    private String issueDate ;
    private String expiryDate ;
    private String billingDate ;
    private String cardPin ;
    private Long cardLimit ;
}
