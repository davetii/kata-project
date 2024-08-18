package software.daveturner.reportservice.service;

import org.springframework.stereotype.Component;
import software.daveturner.model.Person;

import java.util.Optional;

@Component
public class PersonMapper {
    public Optional<Person> map(Optional<PersonEntity> entity) {
        if (entity == null || entity.isEmpty()) { return Optional.empty(); }
        Person p = new Person();
        p.setFirstName(entity.get().getFirstName());
        p.setLastName(entity.get().getLastName());
        p.setEmail(entity.get().getEmail());
        p.setLocale(entity.get().getLocale());
        p.setId(entity.get().getId());
        p.setHireDate(entity.get().getHireDate());
        p.setLevel(entity.get().getLevel());
        p.setOrg(entity.get().getOrg());
        p.setRole(entity.get().getRole());
        p.setPhone1(entity.get().getPhone1());
        p.setPhone2(entity.get().getPhone2());
        p.setAddr1(entity.get().getAddr1());
        p.setAddr2(entity.get().getAddr2());
        p.setCity(entity.get().getCity());
        p.setRegion(entity.get().getRegion());
        p.setCountry(entity.get().getCountry());
        return Optional.of(p);
    }
}
