package software.daveturner.personwrite.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.daveturner.model.Person;
import software.daveturner.model.PersonWriteRequest;
import software.daveturner.personwrite.repo.PersonWriteRepo;

import java.util.Optional;

@Service
@Transactional
public class PersonWriteServiceImpl implements PersonWriteService {

    private final PersonWriteRepo repo;
    private final PersonWriteHelper utils;
    private final EventProducer eventPublisher;

    public PersonWriteServiceImpl(PersonWriteRepo repo, PersonWriteHelper utils, EventProducer eventPublisher) {
        this.repo = repo;
        this.utils = utils;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Person save(PersonWriteRequest in) {
        Person person = repo.save(utils.writeRequestToPerson(in));
        eventPublisher.publishMessage(utils.createWriteEvent(person));
        return person;
    }

    @Override
    public void delete(String id) {
        repo.deleteById(id);
        eventPublisher.publishMessage(utils.createDeleteEvent(id));
    }

    @Override
    public Optional<Person> findById(String id) {
        return repo.findById(id);
    }

    @Override
    public String fetchNewId() {
        String s = newRandomString();
        if (repo.findById(s).isEmpty()) { return s;}

        s = newRandomString();
        if (repo.findById(s).isEmpty()) { return s;}

        s = newRandomString();
        if (repo.findById(s).isEmpty()) { return s;}

        s = newRandomString();
        if (repo.findById(s).isEmpty()) { return s;}

        s = newRandomString();
        if (repo.findById(s).isEmpty()) { return s;}

        return newRandomString();
    }

    private String newRandomString() {
        return utils.threeRandomLetters() + utils.threeRandomNumbers();
    }

}
