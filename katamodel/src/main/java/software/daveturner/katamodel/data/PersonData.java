package software.daveturner.katamodel.data;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("person")
public class PersonData {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String role;
    private String email;
    private String addr1;
    private String addr2;
    private String city;
    private String region;
    private String country;
    private String phone1;
    private String phone2;

}
