package software.daveturner.personwrite.cucumber;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ConfigForCucumberStepDefs {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
