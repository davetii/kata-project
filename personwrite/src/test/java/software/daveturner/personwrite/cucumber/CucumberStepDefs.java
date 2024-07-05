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

    @Given("server is running for PersonWrite")
    public void server_is_running() {
        String rootURL =  "http://localhost:" + port;
        baseUrl = rootURL + "/api/v1/person";
        //String result = restTemplate.getForObject(rootURL + "/actuator/health" , String.class);
        //System.out.println("actuator result: " + result);
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

    @Then("PersonWrite api returns {string}, {string} , {string} and {string}")
    public void then_PersonWrite_api_returns_id_firstname_lastname_and_role(String id, String firstName, String lastName, String role) {
        Assertions.assertEquals(firstName, person.getFirstName() );
    }
}
