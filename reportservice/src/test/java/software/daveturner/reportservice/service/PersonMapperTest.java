package software.daveturner.reportservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import software.daveturner.model.Person;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class PersonMapperTest {



    PersonMapper mapper;

    @BeforeEach
    public void setup() {
        mapper = new PersonMapper();
    }

    @Test
    public void ensureMapperReturnsExpectedResult() {
        Optional<Person> p = mapper.map(buildPersonEntity());
        assertTrue(p.isPresent());
        assertEquals("123", p.get().getId());
        assertEquals("myFirstName", p.get().getFirstName());
        assertEquals("myLastName", p.get().getLastName());
        assertEquals("SALES", p.get().getOrg());
        assertEquals("LEADER", p.get().getRole());
        assertEquals("en-CA", p.get().getLocale());
        assertEquals(8, p.get().getLevel());
        assertEquals(LocalDate.parse("2020-11-07"), p.get().getHireDate());
        assertEquals("myAddr1", p.get().getAddr1());
        assertEquals("myAddr2", p.get().getAddr2());
        assertEquals("myCity", p.get().getCity());
        assertEquals("USA", p.get().getCountry());
        assertEquals("MI", p.get().getRegion());
        assertEquals("myPhone1", p.get().getPhone1());
        assertEquals("myPhone2", p.get().getPhone2());
        assertEquals("myEmail", p.get().getEmail());
    }

    @Test
    public void ensureMapperHandlesNull() {
        assertTrue(mapper.map(null).isEmpty());
        assertTrue(mapper.map(Optional.empty()).isEmpty());
    }

    private Optional<PersonEntity> buildPersonEntity() {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setId("123");
        personEntity.setCity("myCity");
        personEntity.setFirstName("myFirstName");
        personEntity.setLastName("myLastName");
        personEntity.setEmail("myEmail");
        personEntity.setCountry("USA");
        personEntity.setRegion("MI");
        personEntity.setRole("LEADER");
        personEntity.setOrg("SALES");
        personEntity.setLocale("en-CA");
        personEntity.setAddr1("myAddr1");
        personEntity.setAddr2("myAddr2");
        personEntity.setCity("myCity");
        personEntity.setPhone1("myPhone1");
        personEntity.setPhone2("myPhone2");
        personEntity.setLevel(8);
        personEntity.setHireDate(LocalDate.parse("2020-11-07"));
        return Optional.of(personEntity);
    }

}