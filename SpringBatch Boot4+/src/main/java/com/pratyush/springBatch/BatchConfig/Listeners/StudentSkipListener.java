package com.pratyush.springBatch.BatchConfig.Listeners;

import com.pratyush.springBatch.Model.Students;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.SkipListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Slf4j
public class StudentSkipListener implements SkipListener<Students,Students> {

    @Override
    public void onSkipInRead(Throwable t) {
        log.error("Skipped while reading", t);
    }

    @Override
    public void onSkipInProcess(Students item, Throwable t) {
        log.warn("Skipped while processing item: {}", item.getFirstName(),t);
    }

    @Override
    public void onSkipInWrite(Students item, Throwable t) {
        log.warn("Skipped while writing item: {}", item, t);
    }

}
