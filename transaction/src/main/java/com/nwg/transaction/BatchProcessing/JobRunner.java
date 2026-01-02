package com.nwg.transaction.BatchProcessing;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JobRunner implements CommandLineRunner {

    private final JobLauncher jobLauncher;
    private final Job creditCardJob;

    public JobRunner(JobLauncher jobLauncher, Job creditCardJob) {
        this.jobLauncher = jobLauncher;
        this.creditCardJob = creditCardJob ;
    }

//    @Override
//    public void run(String... args) throws Exception {
//
//        String jobName = "Credit Card Jobss"; // default
//
//        for (String arg : args) {
//            if (arg.startsWith("--jobName=")) {
//                jobName = arg.split("=")[1];
//            }
//        }
//
//        JobParameters jobParameters = new JobParametersBuilder().addString("jobName", jobName, true) // identifying parameter
//                .toJobParameters();
//
//        jobLauncher.run(creditCardJob, jobParameters);
//    }
@Override
public void run(String... args) throws Exception {
    jobLauncher.run(
            creditCardJob,
            new JobParametersBuilder()
                    .addLong("run.id", System.currentTimeMillis())
                    .toJobParameters()
    );
}

}

