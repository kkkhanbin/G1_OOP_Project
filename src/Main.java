import auth.AuthService;
import comparators.StudentGpaComparator;
import courses.*;
import database.DataStore;
import enums.*;
import exceptions.InvalidSupervisorException;
import factory.UserFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import management.*;
import reports.ReportGenerator;
import research.ResearchPaper;
import research.ResearchProject;
import research.ResearchUtils;
import research.Researcher;
import research.comparators.PaperByCitationsComparator;
import research.comparators.PaperByDateComparator;
import research.comparators.PaperByPagesComparator;
import users.*;

public class Main {
    public static void main(String[] args) {

        // =========================
        // 1. CORE SYSTEM
        // =========================
        Student s1 = new Student(
                "S1", "nurzhan", "123",
                "Nurzhan", "Kalmurat", Gender.MALE,
                "CS", 2, 3.5, 18
        );

        Student s2 = new Student(
                "S2", "aliya", "123",
                "Aliya", "Serik", Gender.FEMALE,
                "IS", 1, 3.9, 15
        );

        Student s4 = new Student(
                "S4", "aidana", "123",
                "Aidana", "Nurlybek", Gender.FEMALE,
                "CS", 4, 3.7, 12
        );

        Teacher t1 = new Teacher(
                "T1", "dias", "123",
                "Dias", "Tanirbergen", Gender.MALE,
                500000, "FIT", SchoolType.FIT, TeacherTitle.PROFESSOR
        );

        Teacher t2 = new Teacher(
                "T2", "miras", "123",
                "Miras", "Ospanov", Gender.MALE,
                480000, "FIT", SchoolType.FIT, TeacherTitle.LECTURER
        );

        Manager manager = new Manager(
                "M1", "manager1", "123",
                "Aruzhan", "Bektur", Gender.FEMALE,
                450000, "Registrar Office", SchoolType.FIT, ManagerType.OR
        );

        Admin admin = new Admin(
                "A1", "admin1", "123",
                "Alina", "Sarsen", Gender.FEMALE,
                400000, "Administration", SchoolType.FIT
        );

        System.out.println("===== CORE SYSTEM =====");

        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s4);

        students.sort(new StudentGpaComparator());

        System.out.println("Students sorted by GPA:");
        for (Student s : students) {
            System.out.println(s);
        }

        // =========================
        // 2. ACADEMIC SYSTEM
        // =========================
        System.out.println();
        System.out.println("===== ACADEMIC SYSTEM =====");

        Course oopCourse = new Course("OOP", 5);

        oopCourse.registerStudent(s1); // should fail: 18 + 5 > 21
        oopCourse.registerStudent(s2); // should pass
        oopCourse.registerStudent(s4); // should pass

        oopCourse.addInstructor(t1);

        oopCourse.addLesson(new Lesson(LessonType.LECTURE, "Introduction to OOP", 1));
        oopCourse.addLesson(new Lesson(LessonType.PRACTICE, "Classes and Objects", 2));

        Mark markS2 = new Mark(25, 25, 45);
        Mark markS4 = new Mark(22, 23, 40);

        oopCourse.putMark(s2, markS2);
        oopCourse.putMark(s4, markS4);

        System.out.println(oopCourse);
        System.out.println();

        oopCourse.printStudents();
        System.out.println();

        oopCourse.printLessons();
        System.out.println();

        oopCourse.printMarks();
        System.out.println();

        Transcript transcript = new Transcript();
        transcript.addCourseMark(oopCourse, markS2);
        transcript.printTranscript();

        System.out.println();
        ReportGenerator.generateCourseReport(oopCourse);

        // =========================
        // 3. RESEARCH SYSTEM
        // =========================
        System.out.println();
        System.out.println("===== RESEARCH SYSTEM =====");

        ResearchPaper p1 = new ResearchPaper(
                "AI in Education",
                List.of("Dias Tanirbergen", "Aliya Serik"),
                25,
                "IEEE Access",
                12,
                LocalDate.of(2024, 5, 10),
                "10.1000/182"
        );

        ResearchPaper p2 = new ResearchPaper(
                "Machine Learning for Students",
                List.of("Nurzhan Kalmurat"),
                40,
                "Springer Journal",
                18,
                LocalDate.of(2025, 1, 15),
                "10.1000/183"
        );

        t1.addResearchPaper(p1);
        s2.addResearchPaper(p2);

        t1.setHIndex(5);
        s2.setHIndex(2);

        ResearchProject project = new ResearchProject("Smart University");

        try {
            project.joinProject(t1);
            project.joinProject(s2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Teacher papers sorted by citations:");
        t1.printPapers(new PaperByCitationsComparator());
        System.out.println();

        System.out.println("Student papers sorted by date:");
        s2.printPapers(new PaperByDateComparator());
        System.out.println();

        List<Researcher> researchers = new ArrayList<>();
        researchers.add(t1);
        researchers.add(s2);

        System.out.println("All university papers sorted by pages:");
        ResearchUtils.printAllPapers(researchers, new PaperByPagesComparator());
        System.out.println();

        Researcher topResearcher = ResearchUtils.getTopCitedResearcher(researchers);
        System.out.println("Top cited researcher: " + topResearcher.getResearcherName());

        Researcher topBySchool = ResearchUtils.getTopCitedResearcherBySchool(researchers, SchoolType.FIT);
        if (topBySchool != null) {
            System.out.println("Top cited researcher in FIT: " + topBySchool.getResearcherName());
        }

        Researcher topByYear = ResearchUtils.getTopCitedResearcherByYear(researchers, 2025);
        if (topByYear != null) {
            System.out.println("Top cited researcher in 2025: " + topByYear.getResearcherName());
        }

        try {
            ResearchUtils.assignSupervisor(s2);
        } catch (InvalidSupervisorException e) {
            System.out.println("Supervisor error: " + e.getMessage());
        }

        // =========================
        // 4. 4TH YEAR SUPERVISOR TEST
        // =========================
        System.out.println();
        System.out.println("===== 4TH YEAR SUPERVISOR TEST =====");

        try {
            s4.assignResearchSupervisor(t1);
            System.out.println("Supervisor assigned successfully: " +
                    s4.getResearchSupervisor().getResearcherName());
        } catch (InvalidSupervisorException e) {
            System.out.println("Supervisor error: " + e.getMessage());
        }

        try {
            s4.assignResearchSupervisor(s2);
        } catch (InvalidSupervisorException e) {
            System.out.println("Supervisor error: " + e.getMessage());
        }

        System.out.println("4th year student info:");
        System.out.println(s4);

        // =========================
        // 5. MANAGEMENT SYSTEM
        // =========================
        System.out.println();
        System.out.println("===== MANAGEMENT SYSTEM =====");

        AdminService adminService = new AdminService();
        adminService.addUser(s1);
        adminService.addUser(s2);
        adminService.addUser(s4);
        adminService.addUser(t1);
        adminService.addUser(t2);
        adminService.addUser(manager);
        adminService.addUser(admin);

        ManagerService managerService = new ManagerService(manager);

        Request request = new Request(t1, "Please approve academic mobility request");
        managerService.submitRequest(request);
        managerService.approveRequest(request);

        News news = new News("Midterm Week", "Midterm exams will start next Monday.");
        managerService.publishNews(news);

        Message message = new Message(manager, t2, "Please upload course materials today.");
        managerService.sendMessage(message);

        Course dbCourse = new Course("Databases", 4);
        managerService.assignTeacherToCourse(t2, dbCourse);
        managerService.approveStudentRegistration(s2, dbCourse);

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

        // =========================
        // 6. FINAL SYSTEM (AUTH + STORAGE + FACTORY)
        // =========================
        System.out.println();
        System.out.println("===== FINAL SYSTEM (AUTH + STORAGE + FACTORY) =====");

        DataStore dataStore = DataStore.getInstance();
        dataStore.addUser(s1);
        dataStore.addUser(s2);
        dataStore.addUser(s4);
        dataStore.addUser(t1);
        dataStore.addUser(manager);
        dataStore.addUser(admin);

        dataStore.save("data.ser");

        DataStore loadedData = DataStore.load("data.ser");

        AuthService authService = new AuthService();
        User loggedUser = authService.login("nurzhan", "123", loadedData.getUsers());

        if (loggedUser != null) {
            System.out.println("Login success: " + loggedUser.getFullName());
        } else {
            System.out.println("Login failed");
        }

        User factoryUser = UserFactory.createUser(
                "student",
                "S100",
                "newuser",
                "123",
                "Test",
                "User",
                Gender.MALE
        );

        System.out.println("Created via factory:");
        System.out.println(factoryUser);
    }
}