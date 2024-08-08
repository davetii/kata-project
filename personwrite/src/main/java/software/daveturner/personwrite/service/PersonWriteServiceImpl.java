package software.daveturner.personwrite.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.daveturner.personwrite.model.Person;
import software.daveturner.personwrite.model.PersonWriteData;
import software.daveturner.personwrite.repo.PersonWriteRepo;
import software.daveturner.personwrite.util.PersonWriteUtils;

import java.util.Optional;

@Service
@Transactional
public class PersonWriteServiceImpl implements PersonWriteService {

    private final PersonWriteRepo repo;
    private final PersonMapper mapper;
    private final PersonWriteUtils utils;
    private final EventProducer eventPublisher;



    public PersonWriteServiceImpl(PersonWriteRepo repo, PersonMapper mapper, EventProducer eventPublisher) {
        this.repo = repo;
        this.mapper = mapper;
        this.eventPublisher = eventPublisher;
        this.utils = new PersonWriteUtils();
    }

    @Override
    public Person save(Person person) {
        Person p = mapper.personDataToPerson(repo.save(mapper.personToPersonWriteData(person)));
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString;
        try {
            jsonString = objectMapper.writeValueAsString(person);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if (jsonString != null) {
            eventPublisher.publishMessage(jsonString);
        }
        return p;
    }

    @Override
    public void delete(String id) {
        repo.deleteById(id);
    }

    @Override
    public Optional<Person> findById(String id) {
        Optional<PersonWriteData> pwd = repo.findById(id);
        return pwd.map(mapper::personDataToPerson);
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
