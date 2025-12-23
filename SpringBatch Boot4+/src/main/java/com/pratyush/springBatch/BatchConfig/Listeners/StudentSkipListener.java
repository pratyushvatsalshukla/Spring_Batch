package com.pratyush.springBatch.BatchConfig.Listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pratyush.springBatch.Model.Students;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.SkipListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Slf4j
public class StudentSkipListener implements SkipListener<Students,Students> {
    ObjectMapper mapper = new ObjectMapper() ;

    // Capturing the bad Records in ItemProcessor
    @Override
    public void onSkipInRead(Throwable t) {
        log.error("Skipped while reading", t);
    }

    @Override
    public void onSkipInProcess(Students item, Throwable t) {
        log.warn("Skipped while processing item: {}", item.getFirstName(),t);
        if(t instanceof InterruptedException)
        {
            try {
                log.info("Skipped Processing For Bad Record : [{}]", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(item));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public void onSkipInWrite(Students item, Throwable t) {
        log.warn("Skipped while writing item: {}", item, t);
    }

}
