package com.nwg.transaction.BatchProcessing.Reader;

import com.nwg.transaction.Model.CreditCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Slf4j
public class CreditCardDetailsReader {
    @Autowired
    DataSource dataSource ;
    @Bean
    public JdbcCursorItemReader<CreditCard> creditCardItemsReader(){
        log.info("Inside CreditCardDetailsReader::creditCardDetailsReader ");
        JdbcCursorItemReader<CreditCard> reader = new JdbcCursorItemReader<>() ;
        reader.setFetchSize(100); // LEARN ABOUT IT MORE ! ITS ACTUALLY IMPORTANT TO USE
        reader.setDataSource(dataSource);
        reader.setSql("SELECT * FROM credit_card_transactions LIMIT 10000");
        reader.setRowMapper(new BeanPropertyRowMapper<>(CreditCard.class));
        log.info("Returning JDBC CURSOR READER FROM CreditCardDetailsReader::creditCardDetailsReader");
        return reader ;
    }
}
