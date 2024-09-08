package software.daveturner.reportservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "org")
@Data
public class OrgEntity {

    @Id
    private String id;
    private String leaderId;
    private LocalDate leaderDate;
    private String name;

}
