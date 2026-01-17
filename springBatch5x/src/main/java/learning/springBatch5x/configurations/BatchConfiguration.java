package learning.springBatch5x.configurations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableBatchProcessing
@Slf4j
public class BatchConfiguration {
    // Setting up the Batch InfraStructure !
    @Bean("transactionManager")
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource){
        log.info("Transaction Manager Created !");
        return new DataSourceTransactionManager(dataSource) ;
    }
    @Bean
    public TaskExecutor batchTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor() ;
        executor.setCorePoolSize(8);
        executor.setMaxPoolSize(8);
        executor.setQueueCapacity(0);
        executor.setThreadNamePrefix("2waysms-batch-");
        executor.initialize();
        log.info("Task Executor Created ");
        return executor ;
    }

}
