package com.pratyush.springBatch.BatchConfig.Processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pratyush.springBatch.Model.Students;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class FirstItemProcessor implements ItemProcessor<Students,Students> {
    @Override
    public Students process(Students item) throws Exception {
        ObjectMapper mapper = new ObjectMapper() ;
        if(item.getFirstName().equalsIgnoreCase("peter")){
            throw new InterruptedException() ;
        }
//        log.info("Student : [{}]",mapper.writerWithDefaultPrettyPrinter().writeValueAsString(item));
        return item;
    }
}
