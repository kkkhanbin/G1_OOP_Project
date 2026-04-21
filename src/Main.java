import comparators.*;
import courses.*;
import enums.*;
import java.util.ArrayList;
import java.util.List;
import management.AdminService;
import management.ManagerService;
import management.Message;
import management.News;
import management.Request;
import research.ResearchPaper;
import research.ResearchProject;
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

        System.out.println();
        System.out.println("===== MANAGEMENT SYSTEM =====");

        Admin admin = new Admin("A1", "admin1", "123",
                "Alina", "Sarsen", Gender.FEMALE,
                400000, "Administration");

        Manager manager = new Manager("M1", "manager1", "123",
                "Aruzhan", "Bektur", Gender.FEMALE,
                450000, "Registrar Office", ManagerType.OR);

        Teacher teacher2 = new Teacher("T2", "teacher2", "123",
                "Miras", "Ospanov", Gender.MALE,
                480000, "FIT", TeacherTitle.LECTURER);

        AdminService adminService = new AdminService();
        adminService.addUser(s1);
        adminService.addUser(s2);
        adminService.addUser(t);
        adminService.addUser(manager);
        adminService.addUser(admin);

        ManagerService managerService = new ManagerService(manager);

        Request request = new Request(t, "Please approve academic mobility request");
        managerService.submitRequest(request);
        managerService.approveRequest(request);

        News news = new News("Midterm Week", "Midterm exams will start next Monday.");
        managerService.publishNews(news);

        Message message = new Message(manager, teacher2, "Please upload course materials today.");
        managerService.sendMessage(message);

        Course dbCourse = new Course("Databases", 4);
        managerService.assignTeacherToCourse(teacher2, dbCourse);
        managerService.approveStudentRegistration(s2, dbCourse);

        System.out.println();
        adminService.printUsers();

        System.out.println();
        adminService.printLogs();

        System.out.println();
        managerService.printRequests();

        System.out.println();
        managerService.printNews();

        System.out.println();
        managerService.printMessages();

        System.out.println();
        managerService.printLogs();




    }
}