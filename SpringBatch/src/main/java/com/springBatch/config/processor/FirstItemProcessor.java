package com.springBatch.config.processor;

import com.springBatch.config.model.Students;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FirstItemProcessor implements ItemProcessor<Students, Students> {
    @Override
    public Students process(Students student) throws Exception {
        log.info("Inside Processor :: process ()");
        return student;
    }
    // ItemProcessor<InputType,OutputType>

}
