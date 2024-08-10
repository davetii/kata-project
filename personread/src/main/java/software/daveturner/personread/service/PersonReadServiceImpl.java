package software.daveturner.personread.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.daveturner.katamodel.data.PersonData;
import software.daveturner.personread.model.Person;
import software.daveturner.personread.repo.PersonReadRepo;

import java.util.Optional;

@Service
@Transactional
public class PersonReadServiceImpl implements  PersonReadService {

    private final PersonReadRepo repo;
    private final Mapper mapper;

    public PersonReadServiceImpl(PersonReadRepo repo, Mapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public Optional<Person> findById(String id) {
        System.out.println("findById service IS CALLED");
        Optional<PersonData> pd = repo.findById(id);
        return mapper.personDataToPerson(pd);
    }
}
