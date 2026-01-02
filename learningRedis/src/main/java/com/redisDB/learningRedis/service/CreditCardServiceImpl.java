package com.redisDB.learningRedis.service;

import com.redisDB.learningRedis.entity.CreditCardTransactions;
import com.redisDB.learningRedis.repository.CreditCardTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardServiceImpl implements CreditCardServices{

    @Autowired
    private CreditCardTransactionRepository creditCardTransactionRepository ;

    @Override
    public CreditCardTransactions getCardDetails(String cardNumber) {
        CreditCardTransactions byCardNumber = creditCardTransactionRepository.findByCardNumber(cardNumber);
        return byCardNumber;
    }
}
