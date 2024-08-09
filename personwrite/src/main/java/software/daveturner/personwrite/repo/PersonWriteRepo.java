package software.daveturner.personwrite.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import software.daveturner.personwrite.model.PersonWriteData;

@Component
public interface PersonWriteRepo  extends MongoRepository<PersonWriteData, String> {

}
