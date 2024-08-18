package software.daveturner.reportservice.service;

import software.daveturner.model.Org;
import software.daveturner.model.Person;

import java.util.List;
import java.util.Optional;

public interface ReportService {

    List<Person> fetchByLevel(String level);
    List<Person> fetchByLocale(String locale);
    List<Org> fetchByOrg(String org);
    List<Person> fetchByRole(String role);
    Optional<Person> fetchById(String id);
}
