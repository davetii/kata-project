package software.daveturner.personwrite.service;

import org.junit.jupiter.api.Test;
import software.daveturner.katamodel.events.KataEvent;
import software.daveturner.model.Person;
import software.daveturner.model.PersonWriteRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonWriteHelperTest {

    PersonWriteHelper helper = new PersonWriteHelper();
    @Test
    void threeRandomLetters() {
        assertEquals(helper.threeRandomLetters().length(), 3);
    }

    @Test
    void threeRandomNumbers() {
        assertEquals(helper.threeRandomLetters().length(), 3);
    }

    @Test
    void createWriteEvent() {

        Person p = new Person();
        p.setFirstName("John");
        p.setLastName("Doe");
        p.setId("123");
        KataEvent event = helper.createWriteEvent(p);
        assertEquals(event.getEventType(), "PersonWriteEvent");
    }

    @Test
    void createDeleteEvent() {
        KataEvent event = helper.createDeleteEvent("123");
        assertEquals(event.getEventType(), "PersonDeleteEvent");
        assertEquals("123", event.getBody());
    }

    @Test
    void writeRequestToPerson() {
        PersonWriteRequest p = new PersonWriteRequest();
        p.setFirstName("John");
        p.setLastName("Doe");
        p.setId("123");
        p.setRole(PersonWriteRequest.RoleEnum.DEV);
        Person p2 = helper.writeRequestToPerson(p);
        assertEquals(p2.getFirstName(), "John");
        assertEquals(p2.getLastName(), "Doe");
        assertEquals("123", p2.getId());
        assertEquals("DEV", p2.getRole());
        assertEquals("en-US", p2.getLocale());
    }
}