package software.daveturner.personwrite.service;

import software.daveturner.model.Person;
import software.daveturner.model.PersonWriteRequest;

import java.util.Optional;

public interface PersonWriteService {
    Person save(PersonWriteRequest person);
    void delete(String id);
    Optional<Person> findById(String id);
    String fetchNewId();
}
