package software.daveturner.personread.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import software.daveturner.model.Person;

import java.util.Optional;

@Component
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Optional<Person> getPerson(String key)  {
        String json = redisTemplate.opsForValue().get(key);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return Optional.of(mapper.readValue(json, Person.class));
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }
}

