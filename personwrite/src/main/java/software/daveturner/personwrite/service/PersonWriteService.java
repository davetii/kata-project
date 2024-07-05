package software.daveturner.personwrite.service;

import org.springframework.http.ResponseEntity;
import software.daveturner.personwrite.model.Person;

import java.util.Optional;

public interface PersonWriteService {
    public Person save(Person person);
    public void delete(String id);
    public Optional<Person> findById(String id);
    public String fetchNewId();
}
