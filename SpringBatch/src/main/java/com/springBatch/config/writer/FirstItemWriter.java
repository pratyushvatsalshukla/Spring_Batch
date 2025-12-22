package com.springBatch.config.writer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@Slf4j
public class FirstItemWriter  implements ItemWriter<Long> {

    @Override
    public void write(List<? extends Long> items) throws Exception {
        // ? because Spring Batch writers should accept any subtype produced by processor.
        log.info("Inside Item Reader ");
        items.stream().forEach(System.out::println);
    }
}
