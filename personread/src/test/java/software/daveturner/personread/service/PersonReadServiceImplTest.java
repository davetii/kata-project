package software.daveturner.personread.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import software.daveturner.model.Person;
import software.daveturner.personread.repo.PersonReadRepo;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class PersonReadServiceImplTest {

    @Autowired
    PersonReadServiceImpl service;

    @MockBean
    PersonReadRepo repo;

    @MockBean
    RedisService redisService;

    @Test
    public void ensureFetchByIdReturnsExpectedWithRedisResponse() {
        Person person = new Person();
        person.setId("123");

        Optional<Person> mockedData = Optional.of(person);
        doReturn(mockedData).when(repo).findById(anyString());
        doReturn(mockedData).when(redisService).getPerson(anyString());

        Optional<Person> value = service.findById("123");
        assertTrue(value.isPresent());
        assertEquals(value.get().getId(), person.getId());

        verify(repo, times(0)).findById(anyString());
        verify(redisService, times(1)).getPerson(anyString());
    }

    @Test
    public void ensureFetchByIdReturnsExpectedWithMongoResponse() {
        Person person = new Person();
        person.setId("123");
        Optional<Person> mockedData = Optional.of(person);
        doReturn(mockedData).when(repo).findById(anyString());
        doReturn(Optional.empty()).when(redisService).getPerson(anyString());

        Optional<Person> value = service.findById("123");
        assertTrue(value.isPresent());
        assertEquals(value.get().getId(), person.getId());

        verify(repo, times(1)).findById(anyString());
        verify(redisService, times(1)).getPerson(anyString());
    }
}