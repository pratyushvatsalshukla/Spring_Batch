package com.nwg.transaction.BatchProcessing.writer;

import com.nwg.transaction.Model.CreditCardRequiredDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

//@Component
//@Slf4j
//public class CreditCardRecordWriter implements ItemWriter<CreditCardRequiredDetails> {
//
//    @Autowired
//    DataSource dataSource;
//
//    @Override
//    public void write(Chunk<? extends CreditCardRequiredDetails> chunk) throws Exception {
//        JdbcBatchItemWriter<Object> build = new JdbcBatchItemWriterBuilder<>()
//                .dataSource(dataSource)
//                .sql("INSERT INTO CreditCardRequiredDetails (" +
//                        "    card_type_code," +
//                        "    card_type_full_name," +
//                        "    issuing_bank," +
//                        "    card_number," +
//                        "    card_holder_name," +
//                        "    cvv_cvv2," +
//                        "    expiry_date" +
//                        ")" +
//                        "VALUES (" +
//                        "    :cardTypeCode," +
//                        "    :cardTypeFullName," +
//                        "    :issuingBank," +
//                        "    :cardNumber," +
//                        "    :cardHolderName," +
//                        "    :cvvCvv2," +
//                        "    :expiryDate" +
//                        ");")
//                .beanMapped()
//                .build();
//        build.afterPropertiesSet();
//        return build.write(chunk);
//    }
//}
@Configuration
@Slf4j
public class CreditCardRecordWriter{
    @Bean
    public JdbcBatchItemWriter<CreditCardRequiredDetails> creditCardWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<CreditCardRequiredDetails>()
                .dataSource(dataSource)
                .sql("""
                    INSERT INTO CreditCardRequiredDetails (
                        card_type_code,
                        card_type_full_name,
                        issuing_bank,
                        card_number,
                        card_holder_name,
                        cvv_cvv2,
                        expiry_date
                    )
                    VALUES (
                        :cardTypeCode,
                        :cardTypeFullName,
                        :issuingBank,
                        :cardNumber,
                        :cardHolderName,
                        :cvvCvv2,
                        :expiryDate
                    )
                """)
                .beanMapped()
                .build();
    }
}