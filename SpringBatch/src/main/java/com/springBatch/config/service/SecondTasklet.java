package com.springBatch.config.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SecondTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("This is the 2nd TASKLET Step");
        log.info("Chunk Job Execution Context : {}",chunkContext.getStepContext().getJobExecutionContext());
        log.info("Chunk Step Execution Context : {}",chunkContext.getStepContext().getStepExecutionContext().toString());
        return RepeatStatus.FINISHED;
    }
};

