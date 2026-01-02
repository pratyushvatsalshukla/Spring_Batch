package com.redisDB.learningRedis.service;

import com.redisDB.learningRedis.entity.CreditCardTransactions;
import org.springframework.stereotype.Service;

public interface CreditCardServices {
    CreditCardTransactions getCardDetails(String cardNumber) ;
}
