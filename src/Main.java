import comparators.*;
import courses.*;
import enums.*;
import java.util.ArrayList;
import java.util.List;
import users.*;

public class Main {
    public static void main(String[] args) {

        Student s1 = new Student("S1", "nurzhan", "123",
                "Nurzhan", "Kalmurat", Gender.MALE,
                "CS", 2, 3.5, 18);

        Student s2 = new Student("S2", "aliya", "123",
                "Aliya", "Serik", Gender.FEMALE,
                "IS", 1, 3.9, 15);

        Teacher t = new Teacher("T1", "dias", "123",
                "Dias", "Tanirbergen", Gender.MALE,
                500000, "FIT", TeacherTitle.PROFESSOR);

        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);

        students.sort(new StudentGpaComparator());

        System.out.println("Students sorted by GPA:");
        for (Student s : students) {
            System.out.println(s);
        }

        Course course = new Course("OOP", 5);

        course.registerStudent(s1);
        course.registerStudent(s2);
        course.addInstructor(t);

        course.addLesson(new Lesson(LessonType.LECTURE, "Introduction to OOP", 1));
        course.addLesson(new Lesson(LessonType.PRACTICE, "Classes and Objects", 2));

        Mark m2 = new Mark(25, 25, 45);
        course.putMark(s2, m2);

        System.out.println();
        System.out.println(course);

        System.out.println();
        course.printStudents();

        System.out.println();
        course.printLessons();

        System.out.println();
        course.printMarks();

        Transcript transcript = new Transcript();
        transcript.addCourseMark(course, m2);

        System.out.println();
        transcript.printTranscript();
    }
}