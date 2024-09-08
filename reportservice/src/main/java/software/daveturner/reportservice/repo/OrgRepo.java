package software.daveturner.reportservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import software.daveturner.reportservice.model.OrgEntity;

@Component
public interface OrgRepo extends JpaRepository<OrgEntity, String> {
}
