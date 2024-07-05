package software.daveturner.personwrite.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import software.daveturner.personwrite.model.Person;
import software.daveturner.personwrite.service.PersonWriteService;

import java.util.Optional;

@RestController
public class V1ApiDelegateImpl implements V1ApiDelegate{

    private final PersonWriteService service;

    public V1ApiDelegateImpl(PersonWriteService service) {
        this.service = service;
    }

    /*
    @Override
    public ResponseEntity<Person> createPerson(String name, Person person) {
        return ResponseEntity.ok(savePerson(person));
    }

    @Override
    public ResponseEntity<String> sayHello(String name) {
        return ResponseEntity.ok("Hello " + name);
    }

    @Override
    public ResponseEntity<Person> updatePerson(String name, Person person) {

    }
    */

    @Override
    public ResponseEntity<Void>  deletePerson(String id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Person> fetchPerson(String personId) {
        Optional<Person> p = service.findById(personId);
        if (p.isPresent()) return ResponseEntity.ok(p.get());
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Person> upsertPerson(Person person) {
        return ResponseEntity.ok(service.save(person));
    }
}
