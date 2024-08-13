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
    private final RedisService redisService;

    public PersonReadServiceImpl(PersonReadRepo repo, RedisService redisService) {
        this.repo = repo;
        this.redisService = redisService;
    }

    @Override
    public Optional<Person> findById(String id) {
        Optional<Person> p = redisService.getPerson(id);
        if (p.isPresent()) { return p; }
        return repo.findById(id);
    }
}
