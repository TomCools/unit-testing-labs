package solutions.custommatcher.domain;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class IsPersonMatcher extends TypeSafeMatcher<Person> {

    private Person personToMatch;

    public IsPersonMatcher(Person person) {
        personToMatch = person;
    }

    public static IsPersonMatcher isPerson(Person person) {
        return new IsPersonMatcher(person);
    }

    @Override
    protected boolean matchesSafely(Person person) {
        return person.getFirstName().equals(personToMatch.getFirstName())
                && person.getLastName().equals(personToMatch.getLastName())
                && person.getAdres().equals(personToMatch.getAdres());
    }

    public void describeTo(Description description) {
        description.appendText("The person has a different Name or Adres.");
    }
}
