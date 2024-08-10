package software.daveturner.personread.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import software.daveturner.personread.model.Person;
import software.daveturner.personread.service.PersonReadService;

import java.util.Optional;

@RestController
public class V1ApiDelegateImpl implements  V1ApiDelegate {

    private static final Logger log = LoggerFactory.getLogger(V1ApiDelegateImpl.class);
    private final PersonReadService service;

    public V1ApiDelegateImpl(PersonReadService service) {
        this.service = service;
        log.info("DTurner V1ApiDelegateImpl is constructred");


    }
    @Override
    public ResponseEntity<Person> fetchPerson(String personId) {
        log.info("DTurner Fetching person: " + personId);
        Optional<Person> p = service.findById(personId);
        return p.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
