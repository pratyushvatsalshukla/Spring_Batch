package com.springBatch.config.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FirstStepListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("Before Step : {}", stepExecution.getStepName());
        log.info("Before Job Execution Context : {}", stepExecution.getJobExecution().getExecutionContext());
        log.info("Before Step Execution Context : {}", stepExecution.getExecutionContext());
        stepExecution.getExecutionContext().put("SEC","SEC VAL");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("After Step : {}", stepExecution.getStepName());
        log.info("After Execution Context : {}", stepExecution.getJobExecution().getExecutionContext());
        log.info("After Job Execution Context : {}", stepExecution.getExecutionContext());
        return null ;
    }
}
