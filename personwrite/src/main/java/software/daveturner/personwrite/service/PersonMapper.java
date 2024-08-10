package software.daveturner.personwrite.service;

import org.springframework.stereotype.Component;
import software.daveturner.personwrite.model.Person;
import software.daveturner.katamodel.data.PersonData;

@Component
public class PersonMapper {

    public PersonData personToPersonWriteData(Person person) {
        PersonData pw = new PersonData();
        pw.setId(person.getId());
        pw.setFirstName(person.getFirstName());
        pw.setLastName(person.getLastName());
        pw.setEmail(person.getEmail());
        pw.setAddr1(person.getAddr1());
        pw.setAddr2(person.getAddr2());
        pw.setCity(person.getCity());
        pw.setRegion(person.getRegion());
        pw.setCountry(person.getCountry());
        pw.setRole(person.getRole().getValue());
        pw.setPhone1(person.getPhone1());
        pw.setPhone2(person.getPhone2());
        return pw;
    }

    public Person personDataToPerson(PersonData pw) {
        Person p = new Person();
        p.setId(pw.getId());
        p.setFirstName(pw.getFirstName());
        p.setLastName(pw.getLastName());
        p.setEmail(pw.getEmail());
        p.setAddr1(pw.getAddr1());
        p.setAddr2(pw.getAddr2());
        p.setCity(pw.getCity());
        p.setRegion(pw.getRegion());
        p.setCountry(pw.getCountry());
        p.setRole(Person.RoleEnum.valueOf(pw.getRole()));
        p.setPhone1(pw.getPhone1());
        p.setPhone2(pw.getPhone2());
        return p;
    }
}
