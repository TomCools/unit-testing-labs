package custommatcher.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Date;

@Value
@Builder
public class Person {
    private String firstName;
    private String lastName;
    private Date birthdate;
    private Adres adres;
}
