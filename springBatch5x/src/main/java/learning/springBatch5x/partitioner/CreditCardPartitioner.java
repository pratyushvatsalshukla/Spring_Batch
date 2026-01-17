package learning.springBatch5x.partitioner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreditCardPartitioner implements Partitioner {

    private final JdbcTemplate jdbcTemplate ;

    @Override

    public Map<String, ExecutionContext> partition(int gridSize) {
        Map<String, ExecutionContext> partitions = new HashMap<>() ;
        for (int i = 0 ; i < gridSize ; i++)
        {
            log.info("Partitioning Happening ");
            ExecutionContext context = new ExecutionContext() ;
            context.putInt("partitionIndex", i);
            context.putInt("totalPartitions",gridSize);
            partitions.put("partition-"+i, context) ;
        }
        return partitions ;
    }
}
