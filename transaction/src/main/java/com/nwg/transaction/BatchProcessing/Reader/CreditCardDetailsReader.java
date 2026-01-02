package com.nwg.transaction.BatchProcessing.Reader;

import com.nwg.transaction.Model.CreditCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.PostgresPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Slf4j
public class CreditCardDetailsReader {
    @Autowired
    DataSource dataSource ;
//    @Bean
//    public JdbcCursorItemReader<CreditCard> creditCardItemsReader(){
//        log.info("Inside CreditCardDetailsReader::creditCardDetailsReader ");
//        JdbcCursorItemReader<CreditCard> reader = new JdbcCursorItemReader<>() ;
//        reader.setFetchSize(100); // LEARN ABOUT IT MORE ! ITS ACTUALLY IMPORTANT TO USE
//        reader.setDataSource(dataSource);
//        reader.setSql("SELECT * FROM credit_card_transactions LIMIT 10000");
//        reader.setRowMapper(new BeanPropertyRowMapper<>(CreditCard.class));
//        log.info("Returning JDBC CURSOR READER FROM CreditCardDetailsReader::creditCardDetailsReader");
//        return reader ;
//    }

    @Bean
    public JdbcPagingItemReader<CreditCard> creditCardItemsReader(){
        JdbcPagingItemReader<CreditCard> reader = new JdbcPagingItemReader<>() ;
        reader.setName("creditCardPagingReader"); // Make ExecutionContext Keys Stable
        reader.setDataSource(dataSource);
        reader.setPageSize(20);     // ??
        reader.setSaveState(true); // ??
        reader.setRowMapper(new BeanPropertyRowMapper<>(CreditCard.class));

        PostgresPagingQueryProvider queryProvider = new PostgresPagingQueryProvider() ;

        queryProvider.setSelectClause("SELECT * ");
        queryProvider.setFromClause("FROM credit_card_transactions");

        Map<String,Order> sortedKeys = new LinkedHashMap<>() ;
        sortedKeys.put("expiry_date", Order.ASCENDING) ;
        sortedKeys.put("card_number", Order.ASCENDING) ;
        queryProvider.setSortKeys(sortedKeys);

        reader.setQueryProvider(queryProvider);
        return reader ;
    }

}
