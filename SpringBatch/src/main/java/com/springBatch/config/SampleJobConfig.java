package com.springBatch.config;

import com.springBatch.config.model.Students;
import com.springBatch.config.processor.FirstItemProcessor;
import com.springBatch.config.writer.FirstItemWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Slf4j
public class SampleJobConfig {

    @Autowired
    private JdbcCursorItemReader jdbcCursorItemReader ;
    @Autowired
    private FirstItemWriter firstItemWriter ;
    @Autowired
    private FirstItemProcessor firstItemProcessor ;

    // -------------------Build JOB
    @Bean
    public Job chunkJob(JobRepository jobRepository, Step firstChunkStep)
    {
        log.info("Inside Chunk Job For Spring Boot 4.x+");
        return new JobBuilder("University Job",jobRepository)
                .start(firstChunkStep)
                .build() ;
    }

    //------------------Build STEPS

    @SuppressWarnings("removal")
    @Bean
    public Step firstChunkStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("First Chunk Step ",jobRepository)
                .<Students,Students>chunk(3,platformTransactionManager)
                .reader(jdbcCursorItemReader)
                .processor(firstItemProcessor)
                .writer(firstItemWriter)
                .build() ;
    }

    public JdbcCursorItemReader<Students> jdbcCursorItemReader(){
        JdbcCursorItemReader<Students> reader = new JdbcCursorItemReader<>() ;
        reader.setDataSource(univesityDataSource);
    }

}
