package com.pratyush.springBatch.BatchConfig.Writer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pratyush.springBatch.Model.Students;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@Slf4j
//public class StudentWriter implements ItemWriter<Students> {
//    @Autowired
//    DataSource dataSource ;
//    @Override
//    public void write(Chunk<? extends Students> chunk) throws Exception {
//        ObjectMapper mapper = new ObjectMapper() ;
//        chunk.forEach(student -> {
//            try {
//                log.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(student));
//                student.setFirstName(student.getFirstName().toUpperCase());
//                studentToDbWriter(student,dataSource) ;
//            } catch (JsonProcessingException e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }
public class StudentWriterConfig{
    @Bean
    public ItemWriter<Students> studentWriter(DataSource dataSource){
        log.info("Putting Data Back To DATABASE");
        JdbcBatchItemWriter<Students> build = new JdbcBatchItemWriterBuilder<Students>()
                .dataSource(dataSource)
                .sql("UPDATE students SET " +
                        " first_name = :firstName WHERE id = :id")
                .beanMapped()
                .build() ;
        build.afterPropertiesSet();
        return items -> {
            for(Students student : items)
            {
                log.info("Writing Student to DB → id={}, firstName={}",
                        student.getId(), student.getFirstName()) ;
            }
            build.write(items);
        } ;
    }

}
