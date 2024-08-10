package software.daveturner.personread.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import software.daveturner.personread.model.Person;

@Component
public interface PersonReadRepo extends MongoRepository<Person, String> {
}
