package com.redisDB.learningRedis.repository;

import com.redisDB.learningRedis.entity.CreditCardTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardTransactionRepository extends JpaRepository<CreditCardTransactions,String> {

    @Query(nativeQuery = true,
    name = "SELECT cardTypeCode, cardTypeFullName, issuingBank, cardNumber, cardHolderName, cvvCvv2, issueDate, expiryDate, cardLimit FROM credit_card_transactions where card_number = :cardNumber")
    CreditCardTransactions findByCardNumber(String cardNumber) ;

}
