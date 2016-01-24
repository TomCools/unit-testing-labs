package custommatcher.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Adres {
    private String street;
    private int number;
    private String bus;
    private String city;
    private String postalCode;
}
