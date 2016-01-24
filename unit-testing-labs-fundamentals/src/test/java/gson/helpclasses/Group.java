package gson.helpclasses;

import java.util.List;

public class Group {
    private List<User> users;
    private String name;

    public Group(List<User> users, String name) {
        this.users = users;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (users != null ? !users.equals(group.users) : group.users != null) return false;
        return !(name != null ? !name.equals(group.name) : group.name != null);

    }

    @Override
    public int hashCode() {
        int result = users != null ? users.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
