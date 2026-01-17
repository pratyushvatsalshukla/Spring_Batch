package learning.springBatch5x.reader;

import learning.springBatch5x.modal.CreditCardRequiredDetails;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.PostgresPagingQueryProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ReaderConfig {

    @Bean
    @StepScope
    public JdbcPagingItemReader<CreditCardRequiredDetails> creditCardItemReader(
            DataSource dataSource,
            @Value("#{stepExecutionContext['partitionIndex']}") Integer partitionIndex,
            @Value("#{stepExecutionContext['totalPartitions']}") Integer totalPartitions) throws Exception {

        JdbcPagingItemReader<CreditCardRequiredDetails> reader =
                new JdbcPagingItemReader<>();

        reader.setDataSource(dataSource);
        reader.setPageSize(1000);
        reader.setRowMapper(
                new BeanPropertyRowMapper<>(CreditCardRequiredDetails.class)
        );

        PostgresPagingQueryProvider provider =
                new PostgresPagingQueryProvider();

        provider.setSelectClause("""
            SELECT
                card_type_code,
                card_type_full_name,
                issuing_bank,
                card_number,
                card_holder_name,
                cvv_cvv2,
                expiry_date
        """);

        provider.setFromClause("FROM credit_card_transactions");

        provider.setWhereClause(
                "MOD(ABS(hashtext(card_number)), :totalPartitions) = :partitionIndex"
        );

        provider.setSortKeys(Map.of("card_number", Order.ASCENDING));

        reader.setQueryProvider(provider);

        // ✅ FIX: DO NOT USE Map.of HERE
        Map<String, Object> params = new HashMap<>();
        params.put("partitionIndex", partitionIndex);
        params.put("totalPartitions", totalPartitions);
        reader.setParameterValues(params);

        reader.afterPropertiesSet();

        return reader;
    }
}

