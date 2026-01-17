package learning.springBatch5x.step;

import learning.springBatch5x.partitioner.CreditCardPartitioner;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

@Configuration
@RequiredArgsConstructor
public class MasterStepConfig {

    private final JobRepository jobRepository ;
    private final Partitioner creditCardPartitioner ;
//    private final PartitionHandler partitionHandler ;
    private final Step slaveStep ;
    private final TaskExecutor batchTaskExecutor;


    @Bean
    public Step masterStep(){
        return new StepBuilder("masterStep",jobRepository)
                .partitioner(slaveStep.getName(), creditCardPartitioner)
                .step(slaveStep)
                .gridSize(8)
                .taskExecutor(batchTaskExecutor)
                .build() ;
    }

}
