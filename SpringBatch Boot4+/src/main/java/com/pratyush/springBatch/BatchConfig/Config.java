package com.pratyush.springBatch.BatchConfig;

import com.pratyush.springBatch.BatchConfig.Processor.FirstItemProcessor;
//import com.pratyush.springBatch.BatchConfig.Reader.StudentItemReader;
import com.pratyush.springBatch.BatchConfig.Writer.StudentWriter;
import com.pratyush.springBatch.Model.Students;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class Config {

//    @Autowired
//    private StudentItemReader studentReader ;
    @Autowired
    private FirstItemProcessor studentProcessor ;
    @Autowired
    private StudentWriter studentWriter ;
    @Autowired
    DataSource dataSource ;

    @Bean
    public Job sampleJob(JobRepository jobRepository, Step studentStep)
    {
        return new JobBuilder("New Job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(studentStep)
                .build() ;
    }

    @Bean
    @SuppressWarnings({"depcrecated", "removal"})
    public Step studentStep(JobRepository jobRepository,PlatformTransactionManager transactionManager){
        return new StepBuilder("New Step",jobRepository)
                .<Students,Students>chunk(3)
                .reader(studentItemReader())
                .processor(studentProcessor)
                .writer(studentWriter)
                .transactionManager(transactionManager)
                .build() ;
    }

    @Bean
    public JdbcCursorItemReader<Students> studentItemReader(){
        log.info("Inside Student Item Reader");
        JdbcCursorItemReader<Students> reader = new JdbcCursorItemReader<>() ;
        reader.setDataSource(dataSource);
        reader.setSql("SELECT id,first_name,last_name,email FROM students");
        reader.setRowMapper(new BeanPropertyRowMapper<>(Students.class)) ;
        reader.setCurrentItemCount(2);
        reader.setMaxItemCount(8);
        log.info("Returning Student Item Reader");
        return reader ;
    }
}
