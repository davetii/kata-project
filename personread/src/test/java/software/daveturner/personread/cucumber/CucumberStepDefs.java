package software.daveturner.personread.cucumber;

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
import software.daveturner.model.Person;
import software.daveturner.personread.service.RedisService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@ContextConfiguration
@CucumberContextConfiguration
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
@Ignore
public class CucumberStepDefs {

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
        /*
        createPersonInRedis("abc123");
        createPersonInRedis("abc124");
        createPersonInRedis("abc125");
         */
    }

    /*
    private void createPersonInRedis(String id) {
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

     */

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

    @Then("PersonRead findBy returns empty")
    public void then_PersonWrite_findByReturnsEmpty() {
        assertEquals(deleteStatusCode, 404 );
    }

    @Then("PersonRead api returns {string}, {string}, {string}, {string}, {string} and {string}")
    public void personreadApiReturnsAnd(String id, String firstName, String lastName, String role, String org, String hireDate) {
        assertEquals(id, person.getId());
        assertEquals(firstName, person.getFirstName());
        assertEquals(lastName, person.getLastName());
        assertEquals(role, person.getRole());
        assertEquals(org, person.getOrg());
        assertEquals(LocalDate.parse(hireDate), person.getHireDate());
    }
}

