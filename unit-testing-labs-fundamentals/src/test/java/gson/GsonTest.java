package gson;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import gson.domain.Group;
import gson.domain.User;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

/**
 * Schrijf voldoende testen om na te gaan of de Gson library de Objecten User en Group succesvol kan verwerken.
 */
public class GsonTest {

    Gson sut = new Gson();

    DataHelper data = new DataHelper();

    @Test
    public void givenUserObject_whenToJsonIsCalled_returnsExpectedJsonstring() {

        String testing = sut.toJson(data.testUserObject());

        assertThat(testing, is(data.testUserJson()));
    }

    @Test
    public void givenJsonString_whenFromJsonIsCalled_returnExpectedObject() {

        User result = sut.fromJson(data.testUserJson(), User.class);

        assertThat(data.testUserObject(), is(result));
    }

    @Test
    public void givenGroupObject_whenToJsonIsCalled_returnsExpectedJsonstring() {
        Group group = data.testGroupObject();

        String testing = sut.toJson(group);

        assertThat(testing, is(data.testGroupJson()));
    }

    @Test
    public void givenGroupJsonString_whenFromJsonIsCalled_returnExpectedObject() {
        String testGroupJson = data.testGroupJson();

        Group result = sut.fromJson(testGroupJson, Group.class);

        assertThat(data.testGroupObject(), is(result));
    }

    @Test(expected = JsonParseException.class)
    public void givenImproperlyFormattedJsonString_whenFromJsonIsCalled_shouldThrowException() {
        String invalidJson = "[]#";

        sut.fromJson(invalidJson, Group.class);
    }

    @Test
    public void givenUserJsonString_whenFromJsonIsCalledWithGroupClass_shouldReturnEmptyGroupObject() {
        String userJson = data.testUserJson();

        Group group = sut.fromJson(userJson, Group.class);

        assertThat(group, is(not(nullValue())));
    }

    class DataHelper {
        public User testUserObject() {
            return new User("Tom", "Cools");
        }

        public String testUserJson() {
            return "{\"firstName\":\"Tom\",\"lastName\":\"Cools\"}";
        }

        public Group testGroupObject() {
            return new Group(Arrays.asList(testUserObject(), testUserObject()),"TestGroupName");
        }

        public String testGroupJson() {
            return "{\"users\":[{\"firstName\":\"Tom\",\"lastName\":\"Cools\"},{\"firstName\":\"Tom\",\"lastName\":\"Cools\"}],\"name\":\"TestGroupName\"}";
        }
    }
}
