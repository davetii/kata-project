package software.daveturner.personread.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import software.daveturner.personread.model.Person;
import software.daveturner.personread.service.PersonReadService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class V1ApiDelegateImplTest {

    @Autowired
    V1ApiDelegateImpl api;

    @MockBean
    PersonReadService service;

    @Test
    public void ensureFetchPersonReturnsExpected() {
        Person person = new Person();
        person.setId("123");

        Optional<Person> mockedData = Optional.of(person);
        doReturn(mockedData).when(service).findById(anyString());

        ResponseEntity<Person> value = api.fetchPerson("123");
        assertTrue(value.hasBody());
        Assertions.assertEquals(value.getBody().getId(), person.getId());
    }

    @Test
    public void ensureNotFoundReturnsExpected() {
        ResponseEntity<Person> value = api.fetchPerson("123");
        Assertions.assertEquals(value.getStatusCode(), HttpStatus.NOT_FOUND);
    }

}