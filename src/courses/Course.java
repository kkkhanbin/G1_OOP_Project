package courses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import users.Student;
import users.Teacher;

public class Course implements java.io.Serializable {
    private String name;
    private int credits;

    // НОВОЕ: для какой специальности и курса предназначен курс
    private String targetMajor;
    private int targetYear;

    private List<Student> students;
    private List<Teacher> instructors;
    private List<Lesson> lessons;
    private Map<Student, Mark> studentMarks;

    // Старый конструктор — без major/year (для обратной совместимости)
    public Course(String name, int credits) {
        this(name, credits, "Any", 0);
    }

    // НОВОЕ: конструктор с указанием специальности и года
    public Course(String name, int credits, String targetMajor, int targetYear) {
        this.name = name;
        this.credits = credits;
        this.targetMajor = targetMajor;
        this.targetYear = targetYear;
        this.students = new ArrayList<>();
        this.instructors = new ArrayList<>();
        this.lessons = new ArrayList<>();
        this.studentMarks = new HashMap<>();
    }

    public String getName() { return name; }

    public int getCredits() { return credits; }

    public String getTargetMajor() { return targetMajor; }

    public int getTargetYear() { return targetYear; }

    public List<Student> getStudents() { return students; }

    public List<Teacher> getInstructors() { return instructors; }

    public List<Lesson> getLessons() { return lessons; }

    public Map<Student, Mark> getStudentMarks() { return studentMarks; }

    public boolean registerStudent(Student student) {
        if (!student.canTakeMoreCourses()) {
            System.out.println("Cannot register: " + student.getFullName() +
                    " has failed more than 3 times");
            return false;
        }

        if (!student.canRegister(credits)) {
            System.out.println("Cannot register: credit limit exceeded for " +
                    student.getFullName() + " (has " + student.getRegisteredCredits() +
                    " credits, course adds " + credits + ")");
            return false;
        }

        if (students.contains(student)) {
            System.out.println("Student " + student.getFullName() +
                    " is already registered");
            return false;
        }

        // НОВОЕ: проверяем соответствие специальности и года
        if (!targetMajor.equals("Any")) {
            if (!student.getMajor().equals(targetMajor)) {
                System.out.println("Cannot register: " + student.getFullName() +
                        " is not in major " + targetMajor);
                return false;
            }
        }

        if (targetYear != 0 && student.getYearOfStudy() != targetYear) {
            System.out.println("Cannot register: " + student.getFullName() +
                    " is not in year " + targetYear);
            return false;
        }

        students.add(student);
        student.addCredits(credits);
        student.addCourse(this);
        return true;
    }

    public void addInstructor(Teacher teacher) {
        instructors.add(teacher);
        teacher.addCourse(this);
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public void putMark(Student student, Mark mark) {
        if (!students.contains(student)) {
            System.out.println("Student " + student.getFullName() +
                    " is not registered to this course");
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
            System.out.println("  " + student.getFullName() +
                    " | credits so far: " + student.getRegisteredCredits());
        }
    }

    public void printLessons() {
        System.out.println("Lessons for course " + name + ":");
        for (Lesson lesson : lessons) {
            System.out.println("  " + lesson);
        }
    }

    public void printMarks() {
        System.out.println("Marks for course " + name + ":");
        for (Map.Entry<Student, Mark> entry : studentMarks.entrySet()) {
            System.out.println("  " + entry.getKey().getFullName() +
                    " -> " + entry.getValue());
        }
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", credits=" + credits +
                ", targetMajor='" + targetMajor + '\'' +
                ", targetYear=" + (targetYear == 0 ? "Any" : targetYear) +
                ", students=" + students.size() +
                ", instructors=" + instructors.size() +
                '}';
    }
}