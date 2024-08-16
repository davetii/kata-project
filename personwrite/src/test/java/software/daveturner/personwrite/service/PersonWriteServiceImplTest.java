package software.daveturner.personwrite.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import software.daveturner.model.Person;
import software.daveturner.model.PersonWriteRequest;
import software.daveturner.personwrite.repo.PersonWriteRepo;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@SpringBootTest
class PersonWriteServiceImplTest {

    @Autowired
    PersonWriteServiceImpl service;

    @MockBean
    EventProducer eventProducer;
    @MockBean
    PersonWriteRepo repo;

    @BeforeEach
    public void setup() {

        Mockito.doAnswer((Answer<Void>) invocation -> {
            System.out.println("printing answer to see the mock working");
            Object[] arguments = invocation.getArguments();
            if (arguments != null && arguments.length > 0 && arguments[0] != null) {
                System.out.println("printing the json string arg for the mock: " + arguments[0]);
            }
            return null;
        }).when(eventProducer).publishMessage(any());
    }

    @Test
    public void ensureFindByIdReturnsExpected() {
        Person p = new Person();
        p.setId("123");
        p.setFirstName("Joe");
        p.setLastName("Blow");
        p.setRole("DEV");
        Mockito.doReturn(Optional.of(p)).when(repo).findById(Mockito.anyString());

        Optional<Person> person = service.findById("123");
        assertTrue(person.isPresent());
        verify(repo, times(1)).findById("123");
    }

    @Test
    public void ensureDeleteReturnsExpected() {

        Mockito.doAnswer((Answer<Void>) invocation -> {
            System.out.println("delete item in mocked repo");
            Object[] arguments = invocation.getArguments();
            if (arguments != null && arguments.length > 0 && arguments[0] != null) {
                System.out.println("args: " + arguments[0]);
            }
            return null;
        }).when(repo).deleteById(any());
        Mockito.doReturn(Optional.empty()).when(repo).findById(Mockito.anyString());

        service.delete("123");
        Optional<Person> person = service.findById("123");
        assertTrue(person.isEmpty());

        verify(repo, times(1)).deleteById(any());
        verify(eventProducer, times(1)).publishMessage(any());

    }

    @Test
    public void ensureFetchNewIdReturnsExpected() {
        assertEquals(6, service.fetchNewId().length());
    }

    @Test
    public void ensureSaveExecutionPathReturnsExpected() {

        PersonWriteRequest pwr = new PersonWriteRequest();
        pwr.setId("123");
        pwr.setFirstName("Joe");
        pwr.setLastName("Blow");
        pwr.setRole(PersonWriteRequest.RoleEnum.DEV);
        pwr.setEmail("joe@example.com");
        pwr.setLocale(PersonWriteRequest.LocaleEnum.EN_US);
        pwr.setOrg(PersonWriteRequest.OrgEnum.IT);
        pwr.setHireDate(LocalDate.parse("2020-10-01"));


        Person p = new Person();
        p.setId("123");
        p.setFirstName("Joe");
        p.setLastName("Blow");
        p.setRole("DEV");

        Mockito.doAnswer((Answer<Void>) invocation -> {
            System.out.println("saving item to repo");
            Object[] arguments = invocation.getArguments();
            if (arguments != null && arguments.length > 0 && arguments[0] != null) {
                System.out.println("repo: " + arguments[0]);
            }
            return null;
        }).when(repo).save(any());

        Mockito.doReturn(p).when(repo).save(any());
        service.save(pwr);

        verify(repo , times(1)).save(any());
        verify(eventProducer , times(1)).publishMessage(any());
    }
}