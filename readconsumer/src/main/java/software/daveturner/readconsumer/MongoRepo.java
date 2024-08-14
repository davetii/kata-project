package software.daveturner.readconsumer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import software.daveturner.model.Person;

@Component
public interface MongoRepo extends MongoRepository<Person, String> {
}
