package learning.springBatch5x.job;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
@Slf4j
public class CreditCardJobConfiguration {
    private final JobRepository jobRepository ;

    private final Step masterStep ;
    ObjectMapper mapper = new ObjectMapper() ;

    @Bean
    public Job creditCardJob(){
        log.info("Inside creditCardJobs") ;

        return new JobBuilder("creditCardJob", jobRepository)
                .start(masterStep)
                .build() ;
    }
}
