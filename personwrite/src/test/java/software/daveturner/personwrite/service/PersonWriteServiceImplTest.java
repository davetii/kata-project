package software.daveturner.personwrite.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.daveturner.personwrite.model.Person;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonWriteServiceImplTest {


    @Autowired
    PersonWriteServiceImpl service;

    @BeforeEach
    public void setup() {
        Person p = new Person();
        p.setId("123");
        p.setFirstName("Joe");
        p.setLastName("Blow");
        p.setRole(Person.RoleEnum.DEV);
        service.save(p);
    }

    @Test
    public void ensureFindByIdReturnsExpected() {
        Optional<Person> person = service.findById("123");
        assertTrue(person.isPresent());
    }

    @Test
    public void ensureDeleteReturnsExpected() {
        service.delete("123");
        Optional<Person> person = service.findById("123");
        assertTrue(person.isEmpty());
    }

    @Test
    public void ensureFetchNewIdReturnsExpected() {
        assertEquals(6, service.fetchNewId().length());
    }

}