//package com.springBatch.config;
//
////import com.springBatch.config.listener.FirstJobListeners;
////import com.springBatch.config.listener.FirstStepListener;
//
//import com.springBatch.config.model.Students;
//import com.springBatch.config.processor.FirstItemProcessor;
//import com.springBatch.config.reader.FirstItemReader;
//import com.springBatch.config.service.SecondTasklet;
//import com.springBatch.config.writer.FirstItemWriter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.item.database.JdbcCursorItemReader;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//
//import javax.sql.DataSource;
//
//@Configuration
//@Slf4j
//public class SampleJob {
////    @Autowired
////    private JobBuilderFactory jobBuilderFactory;
////    @Autowired
////    private StepBuilderFactory stepBuilderFactory;
//    @Autowired
//    private SecondTasklet secondTasklet ;
//    //    @Autowired
////    private FirstJobListeners firstJobListeners ;
////    @Autowired
////    private FirstStepListener firstStepListener ;
////    @Autowired
////    private FirstItemReader firstItemReader ;
//    @Autowired
//    private FirstItemProcessor firstItemProcessor ;
//    @Autowired
//    private FirstItemWriter firstItemWriter ;
////    @Autowired
////    private DataSource dataSource; // to read from SQL data base.
//
////    @Bean
////    @Primary // spring batch should use this for metadata-store
////    @ConfigurationProperties(prefix = "spring.datasource") // chcek app.props prefixes.
////    public DataSource dataSource(){
////        // Hae to create bean using this. As we have used custom datasource. Cant use the above one
////        return DataSourceBuilder.create().build() ;
////    }
////    @Bean
////    @ConfigurationProperties(prefix = "spring.universitydatasource")
////    public DataSource universityDataSource(){
////        // Hae to create bean using this. As we have used custom datasource. Cant use the above one
////        return DataSourceBuilder.create().build() ;
////    }
//    @Autowired
//    private DataSource universityDataSource ;
//
//    //    to have job, spring batch provides Job interface
////    @Bean
////    public Job firstJob() {
////        System.out.println("Inside First Job");
////        return jobBuilderFactory.get("First Job")
////                .incrementer(new RunIdIncrementer())
////                .start(firstStep())
////                .next(secondStep())
////                .listener(firstJobListeners)
////                .build();
////    }
//
//    @Bean
//    public Job chunkJobs() {
//        log.info("Inside Second Job For CHUNK Oriented Step");
//        return jobBuilderFactory.get("Second Job")
//                .incrementer(new RunIdIncrementer())
//                .start(firstChunkStep())
////                .next(secondStep())
//                .build() ;
//    }
//
////    private Step firstStep() {
////        System.out.println("Inside First Step");
////        return stepBuilderFactory.get("First Step")
//
//    /// /                .tasklet(firstTask())
////                .listener(firstStepListener)
////                .build();
////    }
////    private Step secondStep() {
////        System.out.println("Inside Second Step");
////        return stepBuilderFactory.get("Second Step")
////                .tasklet(secondTasklet)
////                .build();
////    }
//    private Step firstChunkStep(){
//        return stepBuilderFactory.get("First Chunk Step ")
//                .<Students, Students>chunk(3)
////                .reader(firstItemReader)
//                .reader(jdbcCursorItemReader())
////                .processor(firstItemProcessor)
//                .writer(firstItemWriter)
//                .build() ;
//    }
//
////    private Tasklet firstTask() {
////        return new Tasklet() {
////            @Override
////            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
////                System.out.println("This is the first TASKLET Step");
////                log.info("Step Execution COntext = {}", chunkContext.getStepContext().getStepExecutionContext());
////                return RepeatStatus.FINISHED; // If Continuable, it will run repeatedly
////            }
////        };
////    }
////    private Tasklet secondTask() {
////        return new Tasklet() {
////            @Override
////            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
////                System.out.println("This is the 2nd TASKLET Step");
////                return RepeatStatus.FINISHED;
////            }
////        };
////    }
//
//    public JdbcCursorItemReader<Students> jdbcCursorItemReader() {
//        JdbcCursorItemReader<Students> jdbcCursorItemReader = new JdbcCursorItemReader<>();
//        jdbcCursorItemReader.setDataSource(universityDataSource);
//        jdbcCursorItemReader.setSql("select id, first_name as firstName, last_name as lastName, email from students ");
//        jdbcCursorItemReader.setRowMapper(new BeanPropertyRowMapper<>(Students.class));
//        jdbcCursorItemReader.setCurrentItemCount(2); // Skip first 2 records from TOP OR start from 3rd record from TOP.
//        jdbcCursorItemReader.setMaxItemCount(8); // this will include the above skipper 2 rows as well => we get 6 rows
//        return jdbcCursorItemReader;
//    }
//}
//
////START FROM VIDEO 21
