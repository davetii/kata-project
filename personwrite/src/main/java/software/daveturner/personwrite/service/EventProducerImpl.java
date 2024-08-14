package software.daveturner.personwrite.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import software.daveturner.katamodel.events.KataEvent;
import java.util.concurrent.CompletableFuture;


@Component
public class EventProducerImpl implements EventProducer {

    private static final Logger logger = LoggerFactory.getLogger(EventProducerImpl.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public EventProducerImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishMessage(KataEvent event) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        objectMapper.registerModule(new Jdk8Module());
        String jsonString;
        try {
            jsonString = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        logger.info("publishMessage");
        logger.info(jsonString);
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("personwrite", jsonString);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                logger.info("successfully published {}", result);
            }
            else {
                logger.info("failed to publish {}", jsonString);
            }
        });
    }
}
