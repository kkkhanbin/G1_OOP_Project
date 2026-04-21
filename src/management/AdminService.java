package management;

import logs.LogRecord;
import users.User;

import java.util.ArrayList;
import java.util.List;

public class AdminService {
    private List<User> users;
    private List<LogRecord> logs;

    public AdminService() {
        this.users = new ArrayList<>();
        this.logs = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
        logs.add(new LogRecord("Added user: " + user.getFullName()));
    }

    public void removeUser(User user) {
        users.remove(user);
        logs.add(new LogRecord("Removed user: " + user.getFullName()));
    }

    public void printUsers() {
        System.out.println("Users:");
        for (User user : users) {
            System.out.println(user);
        }
    }

    public void printLogs() {
        System.out.println("System logs:");
        for (LogRecord log : logs) {
            System.out.println(log);
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public List<LogRecord> getLogs() {
        return logs;
    }
}