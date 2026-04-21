package courses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import users.Student;
import users.Teacher;

public class Course {
    private String name;
    private int credits;

    private List<Student> students;
    private List<Teacher> instructors;
    private List<Lesson> lessons;
    private Map<Student, Mark> studentMarks;

    public Course(String name, int credits) {
        this.name = name;
        this.credits = credits;
        this.students = new ArrayList<>();
        this.instructors = new ArrayList<>();
        this.lessons = new ArrayList<>();
        this.studentMarks = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Teacher> getInstructors() {
        return instructors;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public Map<Student, Mark> getStudentMarks() {
        return studentMarks;
    }

    public boolean registerStudent(Student student) {
        if (!student.canTakeMoreCourses()) {
            System.out.println("Cannot register: student has failed more than 3 times");
            return false;
        }

        if (!student.canRegister(credits)) {
            System.out.println("Cannot register: credit limit exceeded");
            return false;
        }

        if (students.contains(student)) {
            System.out.println("Student is already registered");
            return false;
        }

        students.add(student);
        return true;
    }

    public void addInstructor(Teacher teacher) {
        instructors.add(teacher);
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public void putMark(Student student, Mark mark) {
        if (!students.contains(student)) {
            System.out.println("Student is not registered to this course");
            return;
        }

        studentMarks.put(student, mark);

        if (!mark.isPassed()) {
            student.incrementFailedCourses();
        }
    }

    public Mark getMark(Student student) {
        return studentMarks.get(student);
    }

    public void printStudents() {
        System.out.println("Students in course " + name + ":");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void printLessons() {
        System.out.println("Lessons for course " + name + ":");
        for (Lesson lesson : lessons) {
            System.out.println(lesson);
        }
    }

    public void printMarks() {
        System.out.println("Marks for course " + name + ":");
        for (Map.Entry<Student, Mark> entry : studentMarks.entrySet()) {
            System.out.println(entry.getKey().getFullName() + " -> " + entry.getValue());
        }
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", credits=" + credits +
                ", students=" + students.size() +
                ", instructors=" + instructors.size() +
                ", lessons=" + lessons.size() +
                '}';
    }
}