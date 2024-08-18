package software.daveturner.reportservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.daveturner.model.Org;
import software.daveturner.model.Person;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReportServiceImpl implements ReportService{

    private final ReportRepo repo;
    private final PersonMapper mapper;

    public ReportServiceImpl(ReportRepo repo, PersonMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public List<Person> fetchByLevel(String level) {
        return List.of();
    }

    @Override
    public List<Person> fetchByLocale(String locale) {
        return List.of();
    }

    @Override
    public List<Org> fetchByOrg(String org) {
        return List.of();
    }

    @Override
    public List<Person> fetchByRole(String role) {
        return List.of();
    }

    @Override
    public Optional<Person> fetchById(String id) {
        return mapper.map(repo.findById(id));
    }
}
