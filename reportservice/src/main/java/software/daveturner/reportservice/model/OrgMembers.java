package software.daveturner.reportservice.model;


import jakarta.persistence.*;
import lombok.Data;
import software.daveturner.model.Org;

import java.time.LocalDate;

@Entity
@Table(name = "org_members")
@Data
public class OrgMembers {

    @EmbeddedId
    private OrgMemberId id;
    private String activeFlag = "Y";
    private LocalDate joinedAt;

    @ManyToOne
    @MapsId("orgId")
    @JoinColumn(name = "org_id", nullable = false)
    private OrgEntity org;

    @ManyToOne
    @MapsId("personId")
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;
}
