package com.redisDB.learningRedis.controller;

import com.redisDB.learningRedis.entity.CreditCardTransactions;
import com.redisDB.learningRedis.service.CreditCardServiceImpl;
import com.redisDB.learningRedis.service.CreditCardServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("credit-card")
public class CreditCardController {
    @Autowired
    CreditCardServiceImpl creditCardServices ;

    @GetMapping("/{cardNumber}")
    public ResponseEntity<?> getCreditCardDetails(@PathVariable String cardNumber){
        CreditCardTransactions cardDetails = creditCardServices.getCardDetails(cardNumber);
        if(Objects.nonNull(cardDetails))
        {
            return new ResponseEntity<>(cardDetails, HttpStatus.FOUND) ;
        }
        return new ResponseEntity<>("No Card Details Found ", HttpStatus.BAD_REQUEST) ;
    }
}
