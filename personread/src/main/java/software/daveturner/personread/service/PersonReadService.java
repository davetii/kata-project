package software.daveturner.personread.service;

import software.daveturner.personread.model.Person;

import java.util.Optional;

public interface PersonReadService {
    Optional<Person> findById(String id);
}
