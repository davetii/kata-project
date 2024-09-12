package software.daveturner.reportservice.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import software.daveturner.model.Org;
import software.daveturner.model.Person;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class V1ApiDelegateImplTest {

    @Autowired
    V1ApiDelegateImpl api;

    @Test
    void fetchByLevel() {
        ResponseEntity<List<Person>> r =  api.fetchByLevel("4");
        assertEquals(r.getStatusCode().value(), 200);
        List<Person> people = r.getBody();
        assertEquals(people.size(), 3);
    }

    @Test
    void fetchByLevelNotFound() {
        ResponseEntity<List<Person>> r =  api.fetchByLevel("a");
        assertEquals(r.getStatusCode().value(), 200);
        List<Person> people = r.getBody();
        assertEquals(people.size(), 0);
    }

    @Test
    void fetchByLocale() {
        ResponseEntity<List<Person>> r =  api.fetchByLocale("en-US");
        assertEquals(r.getStatusCode().value(), 200);
        List<Person> people = r.getBody();
        assertEquals(people.size(), 5);
    }

    @Test
    void fetchByLocaleNotFound() {
        ResponseEntity<List<Person>> r =  api.fetchByLocale("en-RA");
        assertEquals(r.getStatusCode().value(), 200);
        assertEquals(r.getBody().size(), 0);
    }

    @Test
    void fetchOrgMembers() {
        ResponseEntity<List<Person>> r =  api.fetchOrgMembers("SERVICE");
        assertEquals(r.getStatusCode().value(), 200);
        List<Person> people = r.getBody();
        assertEquals(people.size(), 1);
    }

    @Test
    void fetchOrgMembersNotFound() {
        ResponseEntity<List<Person>> r =  api.fetchOrgMembers("SERVICES");
        assertEquals(r.getStatusCode().value(), 200);
        assertEquals(r.getBody().size(), 0);
    }

    @Test
    void fetchByRole() {
        ResponseEntity<List<Person>> r =  api.fetchByRole("LEADER");
        assertEquals(r.getStatusCode().value(), 200);
        List<Person> people = r.getBody();
        assertEquals(people.size(), 2);
    }

    @Test
    void fetchByRoleNotFound() {
        ResponseEntity<List<Person>> r =  api.fetchByRole("LEADERS");
        assertEquals(r.getStatusCode().value(), 200);
        List<Person> people = r.getBody();
        assertEquals(people.size(), 0);
    }

    @Test
    void fetchById() {
        ResponseEntity<Person> p =  api.fetchById("abc123");
        assertEquals(p.getBody().getId(), "abc123");
        assertEquals(p.getBody().getEmail(), "salespersonabc123@kataproject.com");
        assertEquals(p.getBody().getFirstName(), "abc123");
        assertEquals(p.getBody().getLastName(), "SalesPerson1");
        assertEquals(p.getBody().getAddr1(), "myaddr1");
        assertEquals(p.getBody().getAddr2(), "myaddr2");
        assertEquals(p.getBody().getCity(), "sales1city");
        assertEquals(p.getBody().getRegion(), "MI");
        assertEquals(p.getBody().getCountry(), "USA");
        assertEquals(p.getBody().getPhone1(), "5868732121");
        assertEquals(p.getBody().getPhone2(), "5868732122");
    }

    @Test
    void fetchByIdNotFound() {
        ResponseEntity<Person> r =  api.fetchById("notfound");
        assertEquals(r.getStatusCode().value(), 404);
    }

    @Test
    void fetchByEmail() {
        ResponseEntity<Person> p =  api.fetchByEmail("salespersonabc123@kataproject.com");
        assertEquals(p.getStatusCode().value(), 200);
        assertEquals(p.getBody().getId(), "abc123");
        assertEquals(p.getBody().getEmail(), "salespersonabc123@kataproject.com");
        assertEquals(p.getBody().getFirstName(), "abc123");
        assertEquals(p.getBody().getLastName(), "SalesPerson1");
        assertEquals(p.getBody().getAddr1(), "myaddr1");
        assertEquals(p.getBody().getAddr2(), "myaddr2");
        assertEquals(p.getBody().getCity(), "sales1city");
        assertEquals(p.getBody().getRegion(), "MI");
        assertEquals(p.getBody().getCountry(), "USA");
        assertEquals(p.getBody().getPhone1(), "5868732121");
        assertEquals(p.getBody().getPhone2(), "5868732122");
    }

    @Test
    void fetchByEmailNotFound() {
        ResponseEntity<Person> r =  api.fetchByEmail("notfound");
        assertEquals(r.getStatusCode().value(), 404);
    }

    @Test
    void fetchOrg() {
        ResponseEntity<Org> response = api.fetchOrg("SERVICE");
        assertEquals(response.getStatusCode().value(), 200);
        Org org = response.getBody();
        assertEquals(org.getName(), "Service Group");
        assertEquals(org.getLeader().getId(), "abc127");
    }

    @Test
    void fetchOrgNotFound() {
        ResponseEntity<Org> response = api.fetchOrg("SERVICES");
        assertEquals(response.getStatusCode().value(), 404);
    }
}