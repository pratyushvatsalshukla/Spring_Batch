package com.springBatch.config.writer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springBatch.config.model.Students;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@Slf4j
public class FirstItemWriter  implements ItemWriter<Students> {

    @Override
    public void write(List<? extends Students> items) throws Exception {
        // ? because Spring Batch writers should accept any subtype produced by processor.
        log.info("Inside Item Writer ");
        ObjectMapper mapper = new ObjectMapper() ;

        items.stream().forEach(item -> {
            try {
                log.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(item));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
