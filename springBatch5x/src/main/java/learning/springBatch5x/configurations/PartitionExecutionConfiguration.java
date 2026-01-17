package learning.springBatch5x.configurations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
//
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class PartitionExecutionConfiguration {

//    @Qualifier("batchTaskExecutor")
//    private final TaskExecutor batchTaskExecutor;
//
//    @Bean
//    public PartitionHandler partitionHandler(Step slaveStep) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper() ;
//        TaskExecutorPartitionHandler handler = new TaskExecutorPartitionHandler() ;
//        handler.setStep(slaveStep);
//        handler.setTaskExecutor(batchTaskExecutor);
//        handler.setGridSize(8);
//        return handler ;
//    }

//}
