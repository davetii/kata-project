package software.daveturner.personwrite.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;



@Component
public class EventProducerImpl implements EventProducer {

    private static final Logger logger = LoggerFactory.getLogger(EventProducerImpl.class);


    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public void publishMessage(String jsonString) {
        logger.info("publishMessage");
        logger.info(jsonString);
        kafkaTemplate.send("personwrite", jsonString);
    }
}
