package com.springBatch.config;

import com.springBatch.config.service.SecondTasklet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SampleJob {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private SecondTasklet secondTasklet ;

    //        to have job, spring batch provides Job interface
    @Bean
    public Job firstJob() {
        System.out.println("Inside First Job");
        return jobBuilderFactory.get("First Job")
                .start(firstStep())
                .next(secondStep())
                .build();
    }

    private Step firstStep() {
        System.out.println("Inside First Step");
        return stepBuilderFactory.get("First Step")
                .tasklet(firstTask())
                .build();
    }
    private Step secondStep() {
        System.out.println("Inside Second Step");
        return stepBuilderFactory.get("Second Step")
                .tasklet(secondTasklet)
                .build();
    }

    private Tasklet firstTask() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("This is the first TASKLET Step");
                return RepeatStatus.FINISHED; // If Continuable, it will run repeatedly
            }
        };
    }
//    private Tasklet secondTask() {
//        return new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                System.out.println("This is the 2nd TASKLET Step");
//                return RepeatStatus.FINISHED;
//            }
//        };
//    }
}
