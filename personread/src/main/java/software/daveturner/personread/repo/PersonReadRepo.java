package software.daveturner.personread.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import software.daveturner.katamodel.data.PersonData;

@Component
public interface PersonReadRepo extends MongoRepository<PersonData, String> {
}
