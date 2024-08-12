package software.daveturner.personwrite.api;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import software.daveturner.personwrite.model.Person;
import software.daveturner.personwrite.service.PersonWriteService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
class V1ApiDelegateImplTest {

    @Autowired
    V1ApiDelegateImpl api;

    @MockBean
    PersonWriteService service;

    @Test
    public void ensureFetchPersonReturnsExpected() {
        Person person = new Person();
        person.setId("123");

        Optional<Person> mockedData = Optional.of(person);
        doReturn(mockedData).when(service).findById(anyString());

        ResponseEntity<Person> value = api.fetchPerson("123");
        assertTrue(value.hasBody());
        assertEquals(value.getBody().getId(), person.getId());
    }

    @Test
    public void ensureDeletePersonReturnsExpected() {
        Mockito.doNothing().when(service).delete(Mockito.anyString());
        api.deletePerson("123");
        Mockito.verify(service, Mockito.times(1)).delete(Mockito.anyString());
    }

    @Test
    public void ensureSavePersonReturnsExpected() {
        Person person = new Person();
        person.setId("123");
        when(service.save(any())).thenReturn(person);
        api.upsertPerson(Mockito.any());
        Mockito.verify(service, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void ensureNotFoundReturnsExpected() {
        ResponseEntity<Person> value = api.fetchPerson("123");
        assertEquals(value.getStatusCode(), HttpStatus.NOT_FOUND);
    }

}