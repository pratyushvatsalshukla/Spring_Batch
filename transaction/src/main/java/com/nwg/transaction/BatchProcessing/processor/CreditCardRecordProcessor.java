package com.nwg.transaction.BatchProcessing.processor;

import com.nwg.transaction.Model.CreditCard;
import com.nwg.transaction.Model.CreditCardRequiredDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreditCardRecordProcessor implements ItemProcessor<CreditCard,CreditCardRequiredDetails> {
    @Override
    public CreditCardRequiredDetails process(CreditCard card) throws Exception {
        log.info("Inside CreditCardRecordProcessor::process for : [{}]",card.getCardNumber());
        return CreditCardRequiredDetails
                .builder()
                .cardTypeCode(card.getCardTypeCode())
                .cardTypeFullName(card.getCardTypeFullName())
                .issuingBank(card.getIssuingBank())
                .cardNumber(card.getCardNumber())
                .cvvCvv2(card.getCvvCvv2())
                .expiryDate(card.getExpiryDate())
                .cardHolderName(card.getCardHolderName())
                .build() ;
    }
}
