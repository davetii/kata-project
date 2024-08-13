package software.daveturner.personread.cucumber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import software.daveturner.personread.model.Person;
import software.daveturner.personread.service.RedisService;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@ContextConfiguration
@CucumberContextConfiguration
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
@Ignore
public class CucumberStepDefs {

    private static final Logger log = LoggerFactory.getLogger(CucumberStepDefs.class);
    @Autowired
    RestTemplate restTemplate;

    @LocalServerPort
    protected int port = 8081;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @MockBean
    RedisService redisService;

    protected int httpStatus;

    protected String baseUrl;

    protected Person person;

    protected int deleteStatusCode;


    @Given("server is running for PersonRead")
    public void server_is_running() {
        String rootURL =  "http://localhost:" + port;
        baseUrl = rootURL + "/api/v1/person";
    }

    @Given("PersonRead is running and contains data")
    public void server_is_running2() {
        server_is_running();
        createPerson("abc123");
        createPerson("abc124");
        createPerson("abc125");
    }

    private void createPerson(String id) {
        Person p = new Person();
        p.setId(id);
        p.setRole("DEV");
        HttpEntity<Person> request = new HttpEntity<>(p);
        ResponseEntity<Person> personEntity = restTemplate.exchange(baseUrl, HttpMethod.PUT, request,Person.class );
        ObjectWriter ow = new ObjectMapper().writer();
        try {
            String json = ow.writeValueAsString(p);
            redisTemplate.opsForValue().set(id, json);
        } catch (JsonProcessingException e) {
            //throw new RuntimeException(e);
            log.error("Error converting to json");
        }


    }

    @When("PersonRead is called with {string}")
    public void personread_is_called_with(String id) {
        try {

            ResponseEntity<Person> responseEntity  = restTemplate.getForEntity(baseUrl + "/" + id, Person.class);
            person = responseEntity.getBody();
            httpStatus = responseEntity.getStatusCode().value();
            System.out.println("http stays : " + httpStatus);
        } catch (HttpClientErrorException e) {
            httpStatus = e.getStatusCode().value();
            System.out.println("exception occurred : " );
        }
    }

    @Then("PersonRead api returns {string}, {string} , {string} and {string}")
    public void then_personread_api_returns_id_firstname_lastname_and_role(String id, String firstName, String lastName, String role) {
        assertEquals(id, person.getId());
        assertEquals(firstName, person.getFirstName());
        assertEquals(lastName, person.getLastName());
        assertEquals(role, person.getRole());

    }

    @Then("PersonRead findBy returns empty")
    public void then_PersonWrite_findByReturnsEmpty() {
        assertEquals(deleteStatusCode, 404 );
    }


}

