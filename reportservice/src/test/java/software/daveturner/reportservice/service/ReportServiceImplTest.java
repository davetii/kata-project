package software.daveturner.reportservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import software.daveturner.model.Org;
import software.daveturner.model.Person;

import java.time.LocalDate;
import java.util.List;
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

    @Test
    public void ensureFetchByIdHandlesNegativeInput() {
        assertTrue(service.fetchById(null).isEmpty());
        assertTrue(service.fetchById("").isEmpty());
        assertTrue(service.fetchById("notfound").isEmpty());
    }

    @Test
    public void ensureFetchByOrgReturnsExpected() {
        List<Person> list = service.fetchByOrg("SERVICE");
        assertEquals(list.size(), 1);
        assertEquals(list.get(0).getId(), "abc126");
    }

    @Test
    public void ensureFetchByOrgHandlesNegativeInput() {
        assertTrue(service.fetchByOrg(null).isEmpty());
        assertTrue(service.fetchByOrg("").isEmpty());
        assertTrue(service.fetchByOrg("notfound").isEmpty());
    }

    @Test
    public void ensureFetchOrgReturnsExpected() {
        Optional<Org> org = service.fetchOrgById("SERVICE");
        assertEquals(org.get().getLeader().getId(), "abc127");
        assertEquals(org.get().getMembers().get(0).getId(), "abc126");
    }

    @Test
    public void ensureFetchOrgHandlesNegativeInput() {
        assertTrue(service.fetchOrgById(null).isEmpty());
        assertTrue(service.fetchOrgById("").isEmpty());
        assertTrue(service.fetchOrgById("notfound").isEmpty());
    }

    @Test
    public void ensureFetchPesonByLevelReturnsExpected() {
        assertEquals(service.fetchByLevel("4").size(), 3);
        assertEquals(service.fetchByLevel("6").size(), 1);
        assertEquals(service.fetchByLevel("5").size(), 1);
    }

    @Test
    public void ensureFetchPesonByRoleReturnsExpected() {
        assertEquals(service.fetchByRole("LEADER").size(), 2);
        assertEquals(service.fetchByRole("SALES").size(), 1);
        assertEquals(service.fetchByRole("CSR").size(), 1);
        assertEquals(service.fetchByRole("OPS").size(), 1);
    }

    @Test
    public void ensureFetchRoleHandlesNegativeInput() {
        assertTrue(service.fetchByRole(null).isEmpty());
        assertTrue(service.fetchByRole("").isEmpty());
        assertTrue(service.fetchByRole("notfound").isEmpty());
    }

    @Test
    public void ensureFetchPesonByLocaleReturnsExpected() {
        assertEquals(service.fetchByLocale("en-US").size(), 5);
    }

    @Test
    public void ensureFetchLocaleHandlesNegativeInput() {
        assertTrue(service.fetchByLocale(null).isEmpty());
        assertTrue(service.fetchByLocale("").isEmpty());
        assertTrue(service.fetchByLocale("notfound").isEmpty());
    }

    @Test
    public void ensureFetchPesonByEmailReturnsExpected() {
        assertEquals(service.fetchByEmail("servicepersonabc123@kataproject.com").get().getId(), "abc126");
    }

    @Test
    public void ensureFetchEmailHandlesNegativeInput() {
        assertTrue(service.fetchByEmail(null).isEmpty());
        assertTrue(service.fetchByEmail("").isEmpty());
        assertTrue(service.fetchByEmail("notfound").isEmpty());
    }

}