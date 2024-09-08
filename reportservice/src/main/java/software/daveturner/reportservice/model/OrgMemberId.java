package software.daveturner.reportservice.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class OrgMemberId implements Serializable {
    private String orgId;
    private String personId;
}
