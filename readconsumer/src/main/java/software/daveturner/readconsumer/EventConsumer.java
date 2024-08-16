package software.daveturner.readconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import software.daveturner.katamodel.events.KataEvent;
import software.daveturner.model.Person;

import java.util.Optional;

@Component
public class EventConsumer {

    private static final Logger log = LoggerFactory.getLogger(EventConsumer.class);

    private final MongoRepo repo;
    private final RedisService redis;

    public EventConsumer(MongoRepo repo, RedisService redis) {
        this.repo = repo;
        this.redis = redis;
    }

    @KafkaListener(id = "myId", topics = "personwrite")
    public void listen(String in) {
        Optional<KataEvent> event = getKataEvent(in);
        if (event.isPresent()) {
            KataEvent kataEvent = event.get();
            if(kataEvent.getEventType().equals("PersonDeleteEvent")) {
                delete(kataEvent.getBody());
            } else {
                save(kataEvent);
            }
        } else {
            log.error("Consumed PersonWrite event but unable to parse string");
            log.error(in);
        }
    }

    protected void delete(String id) {
        log.info("delete from personread: " + id);
        repo.deleteById(id);
        redis.del(id);
    }

    protected void save(KataEvent kataEvent) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        try {
            Person p = mapper.readValue(kataEvent.getBody(), Person.class);
            log.info("save to personread: " + kataEvent.getBody());
            repo.save(p);
            redis.set(p.getId(), kataEvent.getBody());
        } catch (JsonProcessingException e) {
            log.error("Error processing KataEvent");
            log.error(kataEvent.toString());
        }
    }

    protected Optional<KataEvent> getKataEvent(String json) {
        if (json == null || json.isEmpty()) { return Optional.empty(); }
        ObjectMapper mapper = new ObjectMapper();
        try {
            return Optional.of(mapper.readValue(json, KataEvent.class));
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }
}
