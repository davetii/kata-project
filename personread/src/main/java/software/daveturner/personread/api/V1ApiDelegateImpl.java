package software.daveturner.personread.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import software.daveturner.model.Person;
import software.daveturner.personread.service.PersonReadService;

import java.util.Optional;

@RestController
public class V1ApiDelegateImpl implements  V1ApiDelegate {

    private final PersonReadService service;

    public V1ApiDelegateImpl(PersonReadService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Person> fetchPerson(String personId) {
        Optional<Person> p = service.findById(personId);
        return p.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
