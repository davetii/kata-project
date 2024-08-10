package software.daveturner.personread.service;

import org.springframework.stereotype.Component;
import software.daveturner.katamodel.data.PersonData;
import software.daveturner.personread.model.Person;

import java.util.Optional;

@Component
public class Mapper {

    public Optional<Person> personDataToPerson(Optional<PersonData> pw) {
        if (pw.isEmpty()) {
                System.out.println("he was emppty");
                return Optional.empty(); }

        System.out.println("he was NMOT emppty");
        System.out.println(pw.get());
        Person p = new Person();
        p.setId(pw.get().getId());
        p.setFirstName(pw.get().getFirstName());
        p.setLastName(pw.get().getLastName());
        p.setEmail(pw.get().getEmail());
        p.setAddr1(pw.get().getAddr1());
        p.setAddr2(pw.get().getAddr2());
        p.setCity(pw.get().getCity());
        p.setRegion(pw.get().getRegion());
        p.setCountry(pw.get().getCountry());
        p.setRole(pw.get().getRole());
        p.setPhone1(pw.get().getPhone1());
        p.setPhone2(pw.get().getPhone2());
        return Optional.of(p);
    }
}
