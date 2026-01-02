package com.nwg.transaction.BatchProcessing;

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
import org.springframework.beans.factory.annotation.Qualifier;
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
    ItemWriter creditCardRequiredDataWriter ;
    @Autowired
    PlatformTransactionManager transactionManager ;
    @Bean
    @Qualifier("CreditCardJobs")
    public Job creditCardJobs(JobRepository jobRepository, Step step){
        return new JobBuilder("Credit Card Job", jobRepository)
                .incrementer(new RunIdIncrementer()) // you wont be able to resume JOB as it will create a new Job Instance. which will start from beginning.
                .start(step)
                .build() ;
    }

    @Bean
    @SuppressWarnings({"deprecated","removal"})
    public Step fetchDataStep(JobRepository jobRepository, PlatformTransactionManager transactionManager)
    {
        return new StepBuilder("Fetch Data Step", jobRepository)
                .<CreditCard, CreditCardRequiredDetails>chunk(20, transactionManager)
                .reader(creditCardItemsReader)
                .processor(creditCardRecordProcessor)
//                .writer(creditCardRequiredDataWriter)
                .writer(creditCardWriter)
                .faultTolerant()
                .retry(Throwable.class)
                .retryLimit(5)
                .build();
    }

}
