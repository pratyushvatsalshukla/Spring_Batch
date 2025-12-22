package com.pratyush.springBatch.BatchConfig.Writer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pratyush.springBatch.Model.Students;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class StudentWriter implements ItemWriter<Students> {
    @Override
    public void write(Chunk<? extends Students> chunk) throws Exception {
        ObjectMapper mapper = new ObjectMapper() ;
        chunk.forEach(student -> {
            try {
                log.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(student));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
