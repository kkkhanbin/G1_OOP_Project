package management;

import comparators.StudentGpaComparator;
import comparators.UserNameComparator;
import java.util.ArrayList;
import java.util.List;
import logs.LogRecord;
import users.Student;
import users.Teacher;
import users.User;

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

    // НОВОЕ: обновить имя пользователя
    public void updateUserName(User user, String newFirstName, String newLastName) {
        String oldName = user.getFullName();
        user.setFirstName(newFirstName);
        user.setLastName(newLastName);
        logs.add(new LogRecord("Updated user name: " + oldName +
                " -> " + user.getFullName()));
        System.out.println("User updated: " + oldName + " -> " + user.getFullName());
    }

    // НОВОЕ: обновить пароль пользователя
    public void updateUserPassword(User user, String newPassword) {
        user.setPassword(newPassword);
        logs.add(new LogRecord("Updated password for: " + user.getFullName()));
        System.out.println("Password updated for: " + user.getFullName());
    }

    // НОВОЕ: найти пользователя по id
    public User findUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) return user;
        }
        System.out.println("User not found with id: " + id);
        return null;
    }

    // НОВОЕ: найти пользователя по username
    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) return user;
        }
        System.out.println("User not found with username: " + username);
        return null;
    }

    public void printUsers() {
        System.out.println("=== All Users ===");
        for (User user : users) {
            System.out.println("  " + user);
        }
    }

    // НОВОЕ: вывести всех студентов отсортированных по GPA
    public void printStudentsByGpa() {
        System.out.println("=== Students sorted by GPA ===");
        List<Student> studentList = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Student s) studentList.add(s);
        }
        studentList.sort(new StudentGpaComparator());
        for (Student s : studentList) {
            System.out.println("  " + s.getFullName() + " | GPA: " + s.getGpa());
        }
    }

    // НОВОЕ: вывести всех пользователей отсортированных по имени
    public void printUsersSortedByName() {
        System.out.println("=== Users sorted by name ===");
        List<User> sorted = new ArrayList<>(users);
        sorted.sort(new UserNameComparator());
        for (User user : sorted) {
            System.out.println("  " + user.getFullName());
        }
    }

    // НОВОЕ: вывести всех учителей
    public void printTeachers() {
        System.out.println("=== Teachers ===");
        for (User user : users) {
            if (user instanceof Teacher t) {
                System.out.println("  " + t.getFullName() +
                        " | " + t.getTitle() +
                        " | Rating: " + String.format("%.1f", t.getAverageRating()));
            }
        }
    }

    public void printLogs() {
        System.out.println("=== System Logs ===");
        for (LogRecord log : logs) {
            System.out.println("  " + log);
        }
    }

    public List<User> getUsers() { return users; }

    public List<LogRecord> getLogs() { return logs; }
}