package software.daveturner.personwrite.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import software.daveturner.katamodel.events.KataEvent;
import software.daveturner.personwrite.model.Person;

import static org.apache.commons.lang3.StringUtils.*;

class PersonWriteUtilsTest {

    PersonWriteUtils utils  = new PersonWriteUtils();

    @Test
    void ensureThreeRandomLettersAreAlwaysAlpha() {
        Assertions.assertTrue(isAlpha(utils.threeRandomLetters()));
    }

    @Test
    void ensureThreeRandomNUmbersAreAlwaysNumeric() {
        Assertions.assertTrue(isNumeric(utils.threeRandomNumbers()));
    }

    @Test
    public void ensureCreateDeleteEventReturnsExpected() {
        KataEvent e = utils.createDeleteEvent("123");
        Assertions.assertEquals("123", e.getBody());
        Assertions.assertEquals("PersonDeleteEvent", e.getEventType());
    }

    @Test
    public void ensureCreateWriteEventReturnsExpected() throws JsonProcessingException {
        Person p = new Person();
        p.setId("123");
        p.setFirstName("Joe");
        p.setLastName("Blow");
        p.setRole(Person.RoleEnum.DEV);

        KataEvent e = utils.createWriteEvent(p);
        Assertions.assertEquals("PersonWriteEvent", e.getEventType());
        ObjectMapper objectMapper = new ObjectMapper();
        Person p2 = objectMapper.readValue(e.getBody(), Person.class);
        Assertions.assertEquals("123", p2.getId());
        Assertions.assertEquals("Joe", p2.getFirstName());
        Assertions.assertEquals("Blow", p2.getLastName());
        Assertions.assertEquals(Person.RoleEnum.DEV, p2.getRole());
    }
}