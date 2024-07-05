package software.daveturner.personwrite.service;

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


    public PersonWriteServiceImpl(PersonWriteRepo repo, PersonMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
        this.utils = new PersonWriteUtils();
    }

    @Override
    public Person save(Person person) {
        return mapper.personDataToPerson(repo.save(mapper.personToPersonWriteData(person)));
    }

    @Override
    public void delete(String id) {
        repo.deleteById(id);
    }

    @Override
    public Optional<Person> findById(String id) {
        Optional<PersonWriteData> pwd = repo.findById(id);
        if (pwd.isPresent()) {
            return Optional.of(mapper.personDataToPerson(pwd.get()));
        };
        return Optional.empty();
    }

    @Override
    public String fetchNewId() {
        String s = newRandomString();
        if (repo.findById(s).isEmpty()) { return s;};

        s = newRandomString();
        if (repo.findById(s).isEmpty()) { return s;};

        s = newRandomString();
        if (repo.findById(s).isEmpty()) { return s;};

        s = newRandomString();
        if (repo.findById(s).isEmpty()) { return s;};

        s = newRandomString();
        if (repo.findById(s).isEmpty()) { return s;};

        return newRandomString();
    }

    private String newRandomString() {
        return utils.threeRandomLetters() + utils.threeRandomNumbers();
    }

}
