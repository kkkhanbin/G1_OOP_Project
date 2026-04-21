import comparators.*;
import courses.*;
import enums.*;
import research.ResearchPaper;
import research.ResearchProject;

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

        ResearchPaper p1 = new ResearchPaper(
            "AI in Education",
            java.util.List.of("Dias Tanirbergen", "Aliya Serik"),
            25,
            "IEEE Access",
            12,
            java.time.LocalDate.of(2024, 5, 10),
            "10.1000/182"
        );

        ResearchPaper p2 = new ResearchPaper(
            "Machine Learning for Students",
            java.util.List.of("Nurzhan Kalmurat"),
            40,
            "Springer Journal",
            18,
            java.time.LocalDate.of(2025, 1, 15),
            "10.1000/183"
        );

        t.addResearchPaper(p1);
        s2.addResearchPaper(p2);

        t.setHIndex(5);
        s2.setHIndex(2);

        ResearchProject project = new ResearchProject("Smart University");

        try {
            project.joinProject(t);
            project.joinProject(s2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println("Teacher papers sorted by citations:");
        t.printPapers(new research.comparators.PaperByCitationsComparator());

        System.out.println();
        System.out.println("Student papers sorted by date:");
        s2.printPapers(new research.comparators.PaperByDateComparator());

        java.util.List<research.Researcher> researchers = new java.util.ArrayList<>();
        researchers.add(t);
        researchers.add(s2);

        System.out.println();
        System.out.println("All university papers sorted by pages:");
        research.ResearchUtils.printAllPapers(researchers, new research.comparators.PaperByPagesComparator());

        System.out.println();
        research.Researcher topResearcher = research.ResearchUtils.getTopCitedResearcher(researchers);
        System.out.println("Top cited researcher: " + topResearcher.getResearcherName());

        try {
            research.ResearchUtils.assignSupervisor(s2);
        } catch (exceptions.InvalidSupervisorException e) {
            System.out.println("Supervisor error: " + e.getMessage());
        }

    }
}