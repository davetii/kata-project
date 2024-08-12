package software.daveturner.readconsumer;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import software.daveturner.katamodel.events.KataEvent;
import software.daveturner.katamodel.events.PersonDeleteEvent;
import software.daveturner.katamodel.events.PersonWriteEvent;
import software.daveturner.readconsumer.model.Person;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class EventConsumerTest {

    @Autowired
    EventConsumer eventConsumer;


    @MockBean
    MongoRepo repo;

    @MockBean
    RedisService redis;

    PersonWriteEvent  personWriteEvent;
    String eventAsJsonString;



    @BeforeEach
    public void setup() throws JsonProcessingException {

        personWriteEvent = new PersonWriteEvent();
        Person person = new Person();
        person.setId("123");
        person.setFirstName("CustomerFirstName");
        person.setLastName("CustomerLastName");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        personWriteEvent.setBody(ow.writeValueAsString(person));

        eventAsJsonString = ow.writeValueAsString(personWriteEvent);
    }

    @Test
    public void ensureGetKataEventReturnsExpectedWhenGivenNull() {
        assertTrue(eventConsumer.getKataEvent(null).isEmpty());
        assertTrue(eventConsumer.getKataEvent("").isEmpty());
        assertTrue(eventConsumer.getKataEvent("garbly gook").isEmpty());
    }

    @Test
    public void ensureGetKataEventReturnsExpected() throws JsonProcessingException {
        Optional<KataEvent> k =  eventConsumer.getKataEvent(eventAsJsonString);
        assertTrue(k.isPresent());

        ObjectMapper mapper = new ObjectMapper();
        Person testPerson = mapper.readValue(k.get().getBody(), Person.class);
        assertEquals("CustomerFirstName", testPerson.getFirstName());
        assertEquals("CustomerLastName", testPerson.getLastName());
        assertEquals("123", testPerson.getId());
    }

    @Test
    public void ensureSaveReturnsExpected() {

        Person person = new Person();
        person.setId("123");
        person.setFirstName("CustomerFirstName");
        person.setLastName("CustomerLastName");


        when(repo.save(any())).thenReturn(person);
        doNothing().when(redis).set(any(), any());
        eventConsumer.save(personWriteEvent);

        verify(repo, times(1)).save(person);
        verify(redis, times(1)).set(any(), any());
        verify(repo, times(0)).deleteById(anyString());
        verify(redis, times(0)).del(anyString());

    }

    @Test
    public void ensureDeleteReturnsExpected() {
        doNothing().when(repo).deleteById(any());
        doNothing().when(redis).del(any());
        eventConsumer.delete("123");

        verify(repo, times(0)).save(any());
        verify(redis, times(0)).set(any(), any());
        verify(repo, times(1)).deleteById(anyString());
        verify(redis, times(1)).del(anyString());
    }

    @Test
    public void ensureListenExecutesAsExpectedWhenSaveIsRun() {

        Person person = new Person();
        person.setId("123");
        person.setFirstName("CustomerFirstName");
        person.setLastName("CustomerLastName");

        eventConsumer.listen(eventAsJsonString);
        when(repo.save(any())).thenReturn(person);
        doNothing().when(redis).set(any(), any());


        verify(repo, times(1)).save(person);
        verify(redis, times(1)).set(any(), any());
        verify(repo, times(0)).deleteById(anyString());
        verify(redis, times(0)).del(anyString());

    }

    @Test
    public void ensureListenExecutesAsExpectedWheDeleteIsRun() throws JsonProcessingException {

        PersonDeleteEvent e = new PersonDeleteEvent();
        e.setBody("123");
        ObjectMapper mapper = new ObjectMapper();
        String deleteEventJson = mapper.writeValueAsString(e);
        doNothing().when(repo).deleteById(any());
        doNothing().when(redis).del(any());

        eventConsumer.listen(deleteEventJson);


        verify(repo, times(0)).save(Mockito.any());
        verify(redis, times(0)).set(any(), any());
        verify(repo, times(1)).deleteById(anyString());
        verify(redis, times(1)).del(anyString());

    }
}