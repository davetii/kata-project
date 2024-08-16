package software.daveturner.personwrite.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import software.daveturner.model.Person;
import software.daveturner.model.PersonWriteRequest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration
@CucumberContextConfiguration
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
@Ignore
public class CucumberStepDefsTest {

    private static final Logger log = LoggerFactory.getLogger(CucumberStepDefsTest.class);
    @Autowired
    RestTemplate restTemplate;

    @LocalServerPort
    protected int port = 8080;

    protected int httpStatus;

    protected String baseUrl;

    protected Person person;

    protected int deleteStatusCode;


    @Given("server is running for PersonWrite")
    public void server_is_running() {
        String rootURL =  "http://localhost:" + port;
        baseUrl = rootURL + "/api/v1/person";
    }

    @Given("PersonWrite is running and contains data")
    public void server_is_running2() {
        server_is_running();
        createPerson("abc123");
        createPerson("abc124");
        createPerson("abc125");
    }

    private void createPerson(String id) {
        PersonWriteRequest p = new PersonWriteRequest();
        p.setId(id);
        p.setRole(PersonWriteRequest.RoleEnum.DEV);
        p.setOrg(PersonWriteRequest.OrgEnum.HR);
        HttpEntity<PersonWriteRequest> request = new HttpEntity<>(p);
        restTemplate.exchange(baseUrl, HttpMethod.PUT, request,Person.class );
    }

    @When("PersonWrite delete is called for {string}")
    public void personwrite_delete_is_called_for(String id) {
        try {
            restTemplate.exchange(baseUrl + "/" + id, HttpMethod.DELETE,null,String.class);
        } catch (HttpClientErrorException e) {
            log.error("exception occurred in PersonWrite delete");
            log.error(e.toString());
        }

        System.out.println("execute findby");
        try {
            ResponseEntity<Person> responseEntity  = restTemplate.getForEntity(baseUrl + "/" + id, Person.class);
            deleteStatusCode = responseEntity.getStatusCode().value();
        } catch (HttpClientErrorException e) {
            deleteStatusCode = e.getStatusCode().value();
            log.error("exception occurred in PersonWrite delete while calling getForEntity");
            log.error(e.toString());
        }
    }

    @Then("PersonWrite findBy returns empty")
    public void then_PersonWrite_findByReturnsEmpty() {
        assertEquals(deleteStatusCode, 404 );
    }


    @When("PersonWrite put is called with {string}, {string}, {string}, {string}, {string} and {string}")
    public void personwritePutIsCalledWithAnd(String id, String firstName, String lastName, String role, String org, String hireDate) {
        try {
            PersonWriteRequest p = new PersonWriteRequest();
            p.setRole(PersonWriteRequest.RoleEnum.fromValue(role));
            p.setFirstName(firstName);
            p.setLastName(lastName);
            p.setId(id);
            p.setOrg(PersonWriteRequest.OrgEnum.fromValue(org));
            p.setHireDate(LocalDate.parse(hireDate));
            HttpEntity<PersonWriteRequest> request = new HttpEntity<>(p);
            ResponseEntity<Person> personEntity = restTemplate.exchange(baseUrl, HttpMethod.PUT, request,Person.class );
            person = personEntity.getBody();
            httpStatus = personEntity.getStatusCode().value();
        } catch (HttpClientErrorException e) {
            httpStatus = e.getStatusCode().value();
            System.out.println("exception occurred : " );
        }
        
    }

    @Then("PersonWrite api returns {string}, {string}, {string}, {string}, {string} and {string}")
    public void personwriteApiReturnsAnd(String id, String firstName, String lastName, String role, String org, String hireDate) {
        assertEquals(firstName, person.getFirstName() );
        assertEquals(id, person.getId());
        assertEquals(lastName, person.getLastName());
        assertEquals(role, person.getRole());
        assertEquals(org, person.getOrg());
        assertEquals(LocalDate.parse(hireDate), person.getHireDate());
    }
}
