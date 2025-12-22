package com.springBatch.config.writer;

import com.springBatch.config.model.Students;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.item.*;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FirstItemWriter implements ItemWriter<Students> {
    @Override
    public void write(Chunk<? extends Students> chunk) {
        chunk.forEach(student ->
                log.info("Writing student: {}",student) ;
        );
    }
}
