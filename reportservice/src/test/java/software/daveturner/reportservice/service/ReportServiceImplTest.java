package software.daveturner.reportservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import software.daveturner.model.Person;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ComponentScan(basePackages = "software.daveturner.reportservice.*")
class ReportServiceImplTest {

    @Autowired
    ReportService service;

    @Test
    public void ensureFetchByIdReturnsExpectedResult() {

        Optional<Person> p = service.fetchById("abc123");
        assertTrue(p.isPresent());
        assertEquals("abc123", p.get().getId());
        assertEquals("abc123", p.get().getFirstName());
        assertEquals("SalesPerson1", p.get().getLastName());
        //assertEquals("SALES", p.get().getOrg());
        assertEquals("LEADER", p.get().getRole());
        assertEquals("en-US", p.get().getLocale());
        assertEquals(6, p.get().getLevel());
        assertEquals(LocalDate.parse("2020-08-17"), p.get().getHireDate());
        assertEquals("myaddr1", p.get().getAddr1());
        assertEquals("myaddr2", p.get().getAddr2());
        assertEquals("sales1city", p.get().getCity());
        assertEquals("USA", p.get().getCountry());
        assertEquals("MI", p.get().getRegion());
        assertEquals("5868732121", p.get().getPhone1());
        assertEquals("5868732122", p.get().getPhone2());
        assertEquals("salespersonabc123@kataproject.com", p.get().getEmail());
    }

}