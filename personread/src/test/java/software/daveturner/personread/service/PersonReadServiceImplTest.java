package software.daveturner.personread.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import software.daveturner.personread.model.Person;
import software.daveturner.personread.repo.PersonReadRepo;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class PersonReadServiceImplTest {

    @Autowired
    PersonReadServiceImpl service;

    @MockBean
    PersonReadRepo repo;

    @Test
    public void ensureFetchByIdReturnsExpected() {
        Person person = new Person();
        person.setId("123");

        Optional<Person> mockedData = Optional.of(person);
        doReturn(mockedData).when(repo).findById(anyString());

        Optional<Person> value = service.findById("123");
        assertTrue(value.isPresent());
        Assertions.assertEquals(value.get().getId(), person.getId());
    }
}