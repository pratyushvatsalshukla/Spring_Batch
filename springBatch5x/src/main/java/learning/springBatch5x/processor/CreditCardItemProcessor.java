package learning.springBatch5x.processor;

import learning.springBatch5x.modal.CreditCardRequiredDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreditCardItemProcessor implements ItemProcessor<CreditCardRequiredDetails, CreditCardRequiredDetails> {

    @Override
    public CreditCardRequiredDetails process(CreditCardRequiredDetails item) {
        item.setCardHolderName(
                item.getCardHolderName().toUpperCase()
        );
        return item;
    }
}

