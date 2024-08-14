package software.daveturner.personwrite.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import software.daveturner.model.Person;
import software.daveturner.model.PersonWriteRequest;
import software.daveturner.personwrite.service.PersonWriteService;

import java.util.Optional;

@RestController
public class V1ApiDelegateImpl implements V1ApiDelegate{

    private final PersonWriteService service;

    public V1ApiDelegateImpl(PersonWriteService service) {
        this.service = service;
    }
    @Override
    public ResponseEntity<Void>  deletePerson(String id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Person> fetchPerson(String personId) {
        Optional<Person> p = service.findById(personId);
        return p.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Person> upsertPerson(PersonWriteRequest person) {
        return ResponseEntity.ok(service.save(person));
    }
}
