//package com.nwg.transaction.BatchProcessing.shutdown;
//
//import jakarta.annotation.PreDestroy;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.explore.JobExplorer;
//import org.springframework.batch.core.launch.JobOperator;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//public class BatchGracefulShutdown {
//
//    private final JobExplorer jobExplorer;
//    private final JobOperator jobOperator;
//
//    public BatchGracefulShutdown(JobExplorer jobExplorer,
//                                 JobOperator jobOperator) {
//        this.jobExplorer = jobExplorer;
//        this.jobOperator = jobOperator;
//    }
//
//    @PreDestroy
//    public void onShutdown() {
//        log.warn("Application shutdown detected. Checking running batch jobs...");
//
//        jobExplorer.findRunningJobExecutions("Credit Card Jobss")
//                .forEach(execution -> {
//                    try {
//                        if (execution.isRunning()) {
//                            log.warn("Stopping JobExecution id={}", execution.getId());
//                            jobOperator.stop(execution.getId());
//                        } else {
//                            log.info(
//                                    "JobExecution {} already stopping/stopped (status={})",
//                                    execution.getId(),
//                                    execution.getStatus()
//                            );
//                        }
//                    } catch (Exception e) {
//                        log.warn(
//                                "Graceful stop skipped for JobExecution {} (status={})",
//                                execution.getId(),
//                                execution.getStatus()
//                        );
//                    }
//                });
//    }
//
//}
