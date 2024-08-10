package software.daveturner.personwrite.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import software.daveturner.katamodel.data.PersonData;

@Component
public interface PersonWriteRepo  extends MongoRepository<PersonData, String> {

}
