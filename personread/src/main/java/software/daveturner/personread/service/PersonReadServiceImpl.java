package software.daveturner.personread.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.daveturner.personread.model.Person;
import software.daveturner.personread.repo.PersonReadRepo;

import java.util.Optional;

@Service
@Transactional
public class PersonReadServiceImpl implements PersonReadService {

    private final PersonReadRepo repo;

    public PersonReadServiceImpl(PersonReadRepo repo) {
        this.repo = repo;
    }

    @Override
    public Optional<Person> findById(String id) {
        return repo.findById(id);
    }
}
