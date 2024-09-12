package software.daveturner.reportservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.daveturner.model.Org;
import software.daveturner.model.Person;
import software.daveturner.reportservice.model.OrgEntity;
import software.daveturner.reportservice.repo.OrgRepo;
import software.daveturner.reportservice.repo.PersonRepo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReportServiceImpl implements ReportService{

    private final PersonRepo personRepo;
    private final PersonMapper mapper;
    private final OrgRepo orgRepo;

    public ReportServiceImpl(PersonRepo repo, PersonMapper mapper, OrgRepo orgRepo) {
        this.personRepo = repo;
        this.mapper = mapper;
        this.orgRepo = orgRepo;
    }

    @Override
    public List<Person> fetchByLevel(String level) {
        try {
            return mapper.mapList(personRepo.findPersonByLevel(Integer.parseInt(level)));
        }catch (NumberFormatException nfe) {
            return Collections.emptyList();
        }

    }

    @Override
    public List<Person> fetchByLocale(String locale) {
        return mapper.mapList(personRepo.findPersonByLocale(locale));
    }

    @Override
    public List<Person> fetchOrgMembers(String org) {
        return mapper.mapList(personRepo.findPersonsByOrgId(org));
    }

    @Override
    public List<Person> fetchByRole(String role) {
        return mapper.mapList(personRepo.findPersonByRole(role));
    }

    @Override
    public Optional<Person> fetchById(String id) {
        if (id == null || id.isEmpty()) { return Optional.empty(); }
        return mapper.map(personRepo.findById(id));
    }

    @Override
    public Optional<Org> fetchOrgById(String id) {
        if(id == null || id.isEmpty()) { return Optional.empty(); }
        Optional<OrgEntity> orgEntity = orgRepo.findById(id);
        if (orgEntity.isEmpty()) { return Optional.empty(); }
        OrgEntity entity = orgEntity.get();
        Org org = new Org();
        org.setName(entity.getName());
        fetchById(entity.getLeaderId()).ifPresent(org::setLeader);
        org.setMembers(fetchOrgMembers(id));
        return Optional.of(org);
    }

    @Override
    public Optional<Person> fetchByEmail(String email) {
        return mapper.map(personRepo.findPersonByEmail(email));
    }
}
