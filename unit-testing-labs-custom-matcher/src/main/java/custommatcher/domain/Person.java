package custommatcher.domain;

import lombok.Value;

import java.util.Date;

@Value
public class Person {
    private String firstName;
    private String lastName;
    private Date birthdate;
    private Adres adres;

    @java.beans.ConstructorProperties({"firstName", "lastName", "birthdate", "adres"})
    Person(String firstName, String lastName, Date birthdate, Adres adres) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.adres = adres;
    }

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }

    public static class PersonBuilder {
        private String firstName;
        private String lastName;
        private Date birthdate;
        private Adres adres;

        PersonBuilder() {
        }

        public Person.PersonBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Person.PersonBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Person.PersonBuilder birthdate(Date birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public Person.PersonBuilder adres(Adres adres) {
            this.adres = adres;
            return this;
        }

        public Person build() {
            return new Person(firstName, lastName, birthdate, adres);
        }

        public String toString() {
            return "custommatcher.domain.Person.PersonBuilder(firstName=" + this.firstName + ", lastName=" + this.lastName + ", birthdate=" + this.birthdate + ", adres=" + this.adres + ")";
        }
    }
}
