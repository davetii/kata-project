package software.daveturner.reportservice.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import software.daveturner.model.Org;
import software.daveturner.model.Person;
import software.daveturner.reportservice.service.ReportService;

import java.util.List;
import java.util.Optional;

public class V1ApiDelegateImpl implements V1ApiDelegate {

    private final ReportService service;

    public V1ApiDelegateImpl(ReportService service) {
        this.service = service;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return V1ApiDelegate.super.getRequest();
    }

    @Override
    public ResponseEntity<List<Person>> fetchByLevel(String level) {
        return getListResponseEntity(service.fetchByLevel(level));
    }

    @Override
    public ResponseEntity<List<Person>> fetchByLocale(String locale) {
        return getListResponseEntity(service.fetchByLocale(locale));
    }

    @Override
    public ResponseEntity<List<Org>> fetchByOrg(String orgid) {
        List<Org> list = service.fetchByOrg(orgid);
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<List<Person>> fetchByRole(String role) {
        return getListResponseEntity(service.fetchByRole(role));
    }

    private static ResponseEntity<List<Person>> getListResponseEntity(List<Person> persons) {
        if (persons.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(persons);
    }

    @Override
    public ResponseEntity<Person> fetchById(String id) {
        Optional<Person> p = service.fetchById(id);
        return p.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
