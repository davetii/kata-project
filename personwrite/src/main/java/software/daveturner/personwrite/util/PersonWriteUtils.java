package software.daveturner.personwrite.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import software.daveturner.kataeventlib.KataEvent;
import software.daveturner.kataeventlib.PersonDeleteEvent;
import software.daveturner.kataeventlib.PersonWriteEvent;
import software.daveturner.personwrite.model.Person;
import java.util.Random;

@Component
public class PersonWriteUtils {

    public String threeRandomLetters() {
        Random r = new Random();
        char[] letters = {
                (char) (r.nextInt(26) + 'a'),
                (char) (r.nextInt(26) + 'a'),
                (char) (r.nextInt(26) + 'a')
        };
        return new String(letters);
    }

    public String threeRandomNumbers() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(r.nextInt(10));
        sb.append(r.nextInt(10));
        sb.append(r.nextInt(10));
        return new String(sb);
    }


    public KataEvent createWriteEvent(Person person) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString;
        try {
            jsonString = objectMapper.writeValueAsString(person);
            return new PersonWriteEvent(jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public KataEvent createDeleteEvent(String id) {
        return new PersonDeleteEvent(id);
    }
}
