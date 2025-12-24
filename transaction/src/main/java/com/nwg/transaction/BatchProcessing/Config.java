package com.nwg.transaction.BatchProcessing;

import com.nwg.transaction.BatchProcessing.Reader.CreditCardDetailsReader;
import com.nwg.transaction.BatchProcessing.processor.CreditCardRecordProcessor;
import com.nwg.transaction.Model.CreditCard;
import com.nwg.transaction.Model.CreditCardRequiredDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Slf4j
public class Config {
    @Autowired
    ItemReader creditCardItemsReader ;
    @Autowired
    ItemProcessor creditCardRecordProcessor ;
    @Autowired
    ItemWriter creditCardWriter ;
    @Autowired
    PlatformTransactionManager transactionManager ;
    @Bean
    public Job nwgJob(JobRepository jobRepository, Step step){
        return new JobBuilder("Natwest Assignment", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build() ;
    }

    @Bean
//    @SuppressWarnings({"deprecated","removal"})
    public Step fetchDataStep(JobRepository jobRepository, PlatformTransactionManager transactionManager)
    {
        return new StepBuilder("Fetch Data Step",jobRepository)
                .<CreditCard, CreditCardRequiredDetails>chunk(20,transactionManager)
                .reader(creditCardItemsReader)
                .processor(creditCardRecordProcessor)
                .writer(creditCardWriter)
                .build() ;
    }

}
