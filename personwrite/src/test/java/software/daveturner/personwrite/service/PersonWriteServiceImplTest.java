package software.daveturner.personwrite.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import software.daveturner.personwrite.model.Person;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonWriteServiceImplTest {

    @Autowired
    PersonWriteServiceImpl service;

    @MockBean
    EventProducer eventProducer;

    @BeforeEach
    public void setup() {
        Mockito.doAnswer((Answer<Void>) invocation -> {
            System.out.println("printing answer to see the mock working");
            Object[] arguments = invocation.getArguments();
            if (arguments != null && arguments.length > 0 && arguments[0] != null) {
                System.out.println("printing the json string arg for the mock: " + arguments[0]);
            }
            return null;
        }).when(eventProducer).publishMessage(Mockito.any());

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