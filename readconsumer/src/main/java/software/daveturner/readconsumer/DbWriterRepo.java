package software.daveturner.readconsumer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import software.daveturner.readconsumer.model.Person;

@Component
public interface DbWriterRepo extends MongoRepository<Person, String> {
}
