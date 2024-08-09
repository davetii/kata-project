package software.daveturner.personwrite.service;

import software.daveturner.personwrite.model.Person;

import java.util.Optional;

public interface PersonWriteService {
    Person save(Person person);
    void delete(String id);
    Optional<Person> findById(String id);
    String fetchNewId();
}
