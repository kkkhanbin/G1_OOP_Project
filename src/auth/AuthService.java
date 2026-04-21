package auth;

import users.User;

import java.util.List;

public class AuthService {

    public User login(String username, String password, List<User> users) {
        for (User user : users) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}