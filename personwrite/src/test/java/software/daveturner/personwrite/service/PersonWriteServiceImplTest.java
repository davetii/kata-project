package software.daveturner.personwrite.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import software.daveturner.personwrite.model.Person;
import software.daveturner.personwrite.model.PersonWriteData;
import software.daveturner.personwrite.repo.PersonWriteRepo;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PersonWriteServiceImplTest {

    @Autowired
    PersonWriteServiceImpl service;

    @MockBean
    EventProducer eventProducer;
    @MockBean
    PersonWriteRepo repo;

    PersonWriteData personWriteDataMock = buildPersonWrite();

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
    }

    @Test
    public void ensureFindByIdReturnsExpected() {
        Optional<PersonWriteData> mockedPwd = Optional.of(personWriteDataMock);
        Mockito.doReturn(mockedPwd).when(repo).findById(Mockito.anyString());

        Optional<Person> person = service.findById("123");
        assertTrue(person.isPresent());
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
        }).when(repo).deleteById(Mockito.any());
        Mockito.doReturn(Optional.empty()).when(repo).findById(Mockito.anyString());

        service.delete("123");
        Optional<Person> person = service.findById("123");
        assertTrue(person.isEmpty());
    }

    @Test
    public void ensureFetchNewIdReturnsExpected() {
        assertEquals(6, service.fetchNewId().length());
    }

    @Test
    public void ensureSaveExecutionPathReturnsExpected() {

        Mockito.doAnswer((Answer<Void>) invocation -> {
            System.out.println("saving item to repo");
            Object[] arguments = invocation.getArguments();
            if (arguments != null && arguments.length > 0 && arguments[0] != null) {
                System.out.println("repo: " + arguments[0]);
            }
            return null;
        }).when(repo).save(Mockito.any());

        Mockito.doReturn(personWriteDataMock).when(repo).save(Mockito.any());

        Person p = new Person();
        p.setId("123");
        p.setFirstName("Joe");
        p.setLastName("Blow");
        p.setRole(Person.RoleEnum.DEV);

        service.save(p);

    }

    private PersonWriteData buildPersonWrite() {
        PersonWriteData mock = new PersonWriteData();
        mock.setId("123");
        mock.setFirstName("Joe");
        mock.setLastName("Blow");
        mock.setRole("DEV");
        return mock;
    }



}