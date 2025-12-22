package com.pratyush.springBatch.BatchConfig;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JobRunner implements CommandLineRunner {

    private final JobLauncher jobLauncher;
    private final Job studentJob;

    public JobRunner(JobLauncher jobLauncher, Job studentJob) {
        this.jobLauncher = jobLauncher;
        this.studentJob = studentJob;
    }

    @Override
    public void run(String... args) throws Exception {
        jobLauncher.run(
                studentJob,
                new JobParametersBuilder()
                        .addLong("run.id", System.currentTimeMillis())
                        .toJobParameters()
        );
    }
}

