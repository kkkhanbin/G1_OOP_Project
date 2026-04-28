package database;

import users.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataStore implements Serializable {
    private static DataStore instance;
    private List<User> users;
    private List<courses.Course> courses;
    private List<management.News> news;
    private List<management.Request> requests;
    private List<management.Message> messages;

    private DataStore() {
        users = new ArrayList<>();
        courses = new ArrayList<>();
        news = new ArrayList<>();
        requests = new ArrayList<>();
        messages = new ArrayList<>();
    }

    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public List<courses.Course> getCourses() {
        return courses;
    }

    public List<management.News> getNews() {
        return news;
    }

    public List<management.Request> getRequests() {
        return requests;
    }

    public List<management.Message> getMessages() {
        return messages;
    }

    public void addCourse(courses.Course course) {
        courses.add(course);
    }

    public void addNews(management.News newsItem) {
        news.add(newsItem);
    }

    public void addRequest(management.Request request) {
        requests.add(request);
    }

    public void addMessage(management.Message message) {
        messages.add(message);
    }

    public void save(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
            System.out.println("Saved successfully");
        } catch (IOException e) {
            System.out.println("Save error: " + e.getMessage());
        }
    }

    public static DataStore load(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            instance = (DataStore) ois.readObject();
            System.out.println("Loaded successfully");
        } catch (Exception e) {
            System.out.println("Load failed, creating new data");
            instance = new DataStore();
        }
        return instance;
    }
}