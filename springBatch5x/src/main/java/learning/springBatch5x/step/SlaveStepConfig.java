package learning.springBatch5x.step;

import learning.springBatch5x.modal.CreditCards;
import learning.springBatch5x.modal.CreditCardRequiredDetails;
import learning.springBatch5x.writer.CreditCardRequiredDetailsItemWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.SQLException;

@Configuration
@RequiredArgsConstructor
public class SlaveStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final JdbcPagingItemReader<CreditCardRequiredDetails> creditCardItemReader;
    private final ItemProcessor<CreditCardRequiredDetails, CreditCardRequiredDetails> processor;
    private final ItemWriter<CreditCardRequiredDetails> writer;

    @Bean
    public Step slaveStep() {
        return new StepBuilder("slaveStep", jobRepository)
                .<CreditCardRequiredDetails, CreditCardRequiredDetails>chunk(1000, transactionManager)
                .reader(creditCardItemReader)
                .processor(processor)
                .writer(writer)
                .faultTolerant()
                .retryLimit(3)
                .retry(Exception.class)
                .build();
    }
}
