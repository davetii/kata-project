package software.daveturner.readconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import software.daveturner.katamodel.events.KataEvent;
import software.daveturner.readconsumer.model.Person;

import java.util.Optional;

@SpringBootApplication
public class ReadconsumerApplication {

	private static final Logger log = LoggerFactory.getLogger(ReadconsumerApplication.class);
	@Autowired
	DbWriterRepo repo;

	public static void main(String[] args) {
		SpringApplication.run(ReadconsumerApplication.class, args);
	}

	@KafkaListener(id = "myId", topics = "personwrite")
	public void listen(String in) {
		Optional<KataEvent> event = getKataEvent(in);
		if (event.isPresent()) {
			KataEvent kataEvent = event.get();
			if(kataEvent.getEventType().equals("PersonDeleteEvent")) {
				log.info("delete from personread: " + kataEvent.getBody());
				repo.deleteById(kataEvent.getBody());
			} else {
				save(kataEvent);
			}
		} else {
			log.error("Consumed PersonWrite event but unable to parse string");
			log.error(in);
		}
	}

	private void save(KataEvent kataEvent) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Person p = mapper.readValue(kataEvent.getBody(), Person.class);
			log.info("save to personread: " + kataEvent.getBody());
			repo.save(p);
		} catch (JsonProcessingException e) {
			log.error("Error processing KataEvent");
			log.error(kataEvent.toString());
		}
	}

	private Optional<KataEvent> getKataEvent(String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			KataEvent event =mapper.readValue(json, KataEvent.class);
			return Optional.of(event);
		} catch (JsonProcessingException e) {
			return Optional.empty();
		}
	}

}


