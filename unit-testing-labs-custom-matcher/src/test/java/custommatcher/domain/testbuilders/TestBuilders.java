package custommatcher.domain.testbuilders;

import custommatcher.domain.Adres;
import custommatcher.domain.Person;

import java.util.Date;

public class TestBuilders {

    public static Adres.AdresBuilder anAdres() {
        return Adres.builder()
                .street("Hamerplaats")
                .number(2)
                .city("Antwerpen")
                .postalCode("2000");
    }

    public static Person.PersonBuilder aPerson() {
        return Person.builder()
                .firstName("Tom")
                .lastName("Cools")
                .birthdate(new Date())
                .adres(TestBuilders.anAdres().build());
    }
}
