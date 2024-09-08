package software.daveturner.reportservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import software.daveturner.reportservice.model.PersonEntity;

import java.util.List;
import java.util.Optional;

@Component
public interface PersonRepo extends JpaRepository<PersonEntity, String> {

    @Query("SELECT p FROM OrgMembers om JOIN om.person p WHERE om.org.id = :orgId AND om.activeFlag = 'Y'")
    List<PersonEntity> findPersonsByOrgId(@Param("orgId") String orgId);
    List<PersonEntity> findPersonByLevel(@Param("level") Integer level);
    List<PersonEntity> findPersonByRole(@Param("role") String role);
    List<PersonEntity> findPersonByLocale(@Param("locale") String locale);
    Optional<PersonEntity> findPersonByEmail(@Param("email") String email);
}
