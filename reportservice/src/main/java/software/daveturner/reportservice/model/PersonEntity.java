package software.daveturner.reportservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "person")
@Data
public class PersonEntity {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String org;
    private String role;
    private String locale;
    private Integer level = 0;
    private LocalDate hireDate;
    private String email;
    private String addr1;
    private String addr2;
    private String city;
    private String region;
    private String country;
    private String phone1;
    private String phone2;

}
