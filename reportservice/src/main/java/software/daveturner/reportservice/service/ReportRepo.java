package software.daveturner.reportservice.service;

import org.springframework.data.repository.*;
import org.springframework.stereotype.Component;


@Component
public interface ReportRepo extends CrudRepository<PersonEntity, String> {
}
