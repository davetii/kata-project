package software.daveturner.personwrite.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import software.daveturner.personwrite.model.Person;


@RunWith(SpringRunner.class)
@ContextConfiguration
@CucumberContextConfiguration
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
@Ignore
public class CucumberStepDefs {

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
        //String result = restTemplate.getForObject(rootURL + "/actuator/health" , String.class);
        //System.out.println("actuator result: " + result);
    }

    @Given("PersonWrite is running and contains data")
    public void server_is_running2() {
        server_is_running();
        createPerson("abc123");
        createPerson("abc124");
        createPerson("abc125");
    }

    private void createPerson(String id) {
        Person p = new Person();
        p.setId(id);
        p.setRole(Person.RoleEnum.DEV);
        HttpEntity<Person> request = new HttpEntity<>(p);
        ResponseEntity<Person> personEntity = restTemplate.exchange(baseUrl, HttpMethod.PUT, request,Person.class );
    }

    @When("PersonWrite put is called with {string}, {string} , {string} and {string}")
    public void personwwrite_put_is_called_with(String id, String firstName, String lastName, String role) {
        try {
            Person p = new Person();
            p.setRole(Person.RoleEnum.valueOf(role));
            p.setFirstName(firstName);
            p.setLastName(lastName);
            p.setId(id);
            HttpEntity<Person> request = new HttpEntity<>(p);
            ResponseEntity<Person> personEntity = restTemplate.exchange(baseUrl, HttpMethod.PUT, request,Person.class );
            person = personEntity.getBody();
            httpStatus = personEntity.getStatusCode().value();

            System.out.println("http stays : " + httpStatus);
        } catch (HttpClientErrorException e) {
            httpStatus = e.getStatusCode().value();
            System.out.println("exception occurred : " );
        }
    }

    @When("PersonWrite delete is called for {string}")
    public void personwrite_delete_is_called_for(String id) {
        try {
            ResponseEntity<String> response = restTemplate.exchange(baseUrl + "/" + id, HttpMethod.DELETE,null,String.class);
            System.out.println("delete was success " + response.getStatusCode());
        } catch (HttpClientErrorException e) {
            System.out.println("exception occurred : " + e);
        }

        System.out.println("execute findby");
        try {
            ResponseEntity<Person> responseEntity  = restTemplate.getForEntity(baseUrl + "/" + id, Person.class);
            deleteStatusCode = responseEntity.getStatusCode().value();
        } catch (HttpClientErrorException e) {
            deleteStatusCode = e.getStatusCode().value();
            System.out.println("exception occurred : " + deleteStatusCode);
        }
    }

    @Then("PersonWrite api returns {string}, {string} , {string} and {string}")
    public void then_PersonWrite_api_returns_id_firstname_lastname_and_role(String id, String firstName, String lastName, String role) {
        Assertions.assertEquals(firstName, person.getFirstName() );
    }

    @Then("PersonWrite findBy returns empty")
    public void then_PersonWrite_findByReturnsEmpty() {
        Assertions.assertEquals(deleteStatusCode, 404 );
    }


}
