package software.daveturner.reportservice.service;

import org.springframework.stereotype.Component;
import software.daveturner.model.Person;
import software.daveturner.reportservice.model.PersonEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Person mapEntityToPerson(PersonEntity entity) {
        Person p = new Person();
        p.setFirstName(entity.getFirstName());
        p.setLastName(entity.getLastName());
        p.setEmail(entity.getEmail());
        p.setLocale(entity.getLocale());
        p.setId(entity.getId());
        p.setHireDate(entity.getHireDate());
        p.setLevel(entity.getLevel());
        p.setOrg(entity.getOrg());
        p.setRole(entity.getRole());
        p.setPhone1(entity.getPhone1());
        p.setPhone2(entity.getPhone2());
        p.setAddr1(entity.getAddr1());
        p.setAddr2(entity.getAddr2());
        p.setCity(entity.getCity());
        p.setRegion(entity.getRegion());
        p.setCountry(entity.getCountry());
        return p;
    }

    public List<Person> mapList(List<PersonEntity> list) {
        if (list == null || list.isEmpty()) { return Collections.emptyList(); }
        return list.stream().map(this::mapEntityToPerson).collect(Collectors.toList());
    }


}
