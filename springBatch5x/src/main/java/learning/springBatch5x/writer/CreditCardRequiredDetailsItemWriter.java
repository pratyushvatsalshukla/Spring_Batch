package learning.springBatch5x.writer;

import learning.springBatch5x.modal.CreditCardRequiredDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class CreditCardRequiredDetailsItemWriter
        extends JdbcBatchItemWriter<CreditCardRequiredDetails> {

    public CreditCardRequiredDetailsItemWriter(DataSource dataSource) {

        setDataSource(dataSource);

        setSql("""
            INSERT INTO creditcardrequireddetails (
                card_type_code,
                card_type_full_name,
                issuing_bank,
                card_number,
                card_holder_name,
                cvv_cvv2,
                expiry_date
            ) VALUES (
                :cardTypeCode,
                :cardTypeFullName,
                :issuingBank,
                :cardNumber,
                :cardHolderName,
                :cvvCvv2,
                :expiryDate
            )
        """);

        setItemSqlParameterSourceProvider(
                new BeanPropertyItemSqlParameterSourceProvider<>());

        afterPropertiesSet();
    }
}


