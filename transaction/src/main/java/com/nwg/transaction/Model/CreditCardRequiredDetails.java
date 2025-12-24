package com.nwg.transaction.Model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCardRequiredDetails {
    private String cardTypeCode ;
    private String cardTypeFullName ;
    private String issuingBank ;
    private String cardNumber ;
    private String cardHolderName ;
    private String cvvCvv2 ;
    private String expiryDate ;
}
