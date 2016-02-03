package solutions.custommatcher.domain;

import solutions.custommatcher.domain.testbuilders.TestBuilders;
import org.junit.Test;

import static solutions.custommatcher.domain.IsPersonMatcher.isPerson;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Schrijf een Matcher die volgende test laat slagen, uncomment natuurlijk eerst de asserts.
 * De match is succesvol als de Voornaam, Achternaam en het Adres van de persoon gelijk zijn.
 * Zorg ook voor een duidelijke foutmelding als het misgaat.
 */
public class PersonTest {

    @Test
    public void givenOnePerson_whenUsingIsPersonMatcher_shouldBeSuccesfull() {
        Person person = TestBuilders.aPerson().build();

        assertThat(person, isPerson(person));
    }

    @Test
    public void givenTwoIdenticalPersons_whenUsingIsPersonMatcher_shouldBeSuccesfull() {
        Person personA = TestBuilders.aPerson().build();
        Person personB = TestBuilders.aPerson().build();

        assertThat(personA, isPerson(personB));
    }

    @Test(expected = AssertionError.class)
    public void givenTwoPersonsWithDifferentFirstName_whenUsingIsPersonMatcher_shouldFail() {
        Person personA = TestBuilders
                .aPerson()
                .firstName("Tom")
                .build();
        Person personB = TestBuilders
                .aPerson()
                .firstName("Robby")
                .build();

        assertThat(personA, isPerson(personB));
    }

    @Test(expected = AssertionError.class)
    public void givenTwoPersonsLivingOnDifferentStreets_whenUsingIsPersonMatcher_shouldFail() {
        Person personA = TestBuilders
                .aPerson()
                .adres(TestBuilders.anAdres().street("Meir").build())
                .build();
        Person personB = TestBuilders
                .aPerson()
                .adres(TestBuilders.anAdres().street("Bell Telephonelaan").build())
                .build();

        assertThat(personA, isPerson(personB));
    }
}