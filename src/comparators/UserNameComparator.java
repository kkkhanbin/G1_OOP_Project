package comparators;

import java.util.Comparator;
import users.User;

public class UserNameComparator implements Comparator<User> {

    @Override
    public int compare(User u1, User u2) {
        return u1.getFullName().compareTo(u2.getFullName());
    }
}