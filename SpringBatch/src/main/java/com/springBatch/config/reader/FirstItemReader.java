//package com.springBatch.config.reader;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.NonTransientResourceException;
//import org.springframework.batch.item.ParseException;
//import org.springframework.batch.item.UnexpectedInputException;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.stream.Stream;
//
//@Component
//@Slf4j
//public class FirstItemReader implements ItemReader<Integer> {
//    // Responsible for reading record from souce
//    List<Integer> list = Stream.iterate(1, x -> x + 1).limit(50).toList();
//    int i = 0;
//
//    @Override
//    public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//        log.info("Inside Item Reader");
//        Integer item;
//        if (i < list.size()) {
//            item = list.get(i);
//            i++;
//            return item;
//        }
//        item = 0;
//        log.info("Now Returning Null");
//        return null; // no more records to read.
//    }
//}
