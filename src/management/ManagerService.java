package management;

import courses.Course;
import java.util.ArrayList;
import java.util.List;
import logs.LogRecord;
import users.Manager;
import users.Student;
import users.Teacher;

public class ManagerService {
    private Manager manager;
    private List<Request> requests;
    private List<News> newsList;
    private List<Message> messages;
    private List<LogRecord> logs;

    public ManagerService(Manager manager) {
        this.manager = manager;
        this.requests = new ArrayList<>();
        this.newsList = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.logs = new ArrayList<>();
    }

    public void submitRequest(Request request) {
        requests.add(request);
        logs.add(new LogRecord("Request submitted by: " + request.getSender().getFullName()));
    }

    public void approveRequest(Request request) {
        request.approve();
        logs.add(new LogRecord("Request approved: " + request.getContent()));
    }

    public void publishNews(News news) {
        newsList.add(news);
        news.notifyObservers();
        logs.add(new LogRecord("News published: " + news.getTitle()));
    }

    public void sendMessage(Message message) {
        messages.add(message);
        logs.add(new LogRecord("Message sent from " +
                message.getSender().getFullName() + " to " +
                message.getReceiver().getFullName()));
    }

    public void assignTeacherToCourse(Teacher teacher, Course course) {
        course.addInstructor(teacher);
        logs.add(new LogRecord("Teacher " + teacher.getFullName() +
                " assigned to course " + course.getName()));
    }

    public void approveStudentRegistration(Student student, Course course) {
        boolean success = course.registerStudent(student);
        logs.add(new LogRecord("Registration of " + student.getFullName() +
                " to " + course.getName() + ": " + (success ? "approved" : "rejected")));
    }

    public void printRequests() {
        System.out.println("Requests:");
        for (Request request : requests) {
            System.out.println(request);
        }
    }

    public void printNews() {
        System.out.println("News:");
        for (News news : newsList) {
            System.out.println(news);
        }
    }

    public void printMessages() {
        System.out.println("Messages:");
        for (Message message : messages) {
            System.out.println(message);
        }
    }

    public void printLogs() {
        System.out.println("Manager logs:");
        for (LogRecord log : logs) {
            System.out.println(log);
        }
    }

    public List<Request> getRequests() { return requests;}
}