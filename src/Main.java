import auth.AuthService;
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
        // 1. USERS
        // =========================
        Student s1 = new Student(
                "S1", "nurzhan", "123",
                "Nurzhan", "Kalmurat", Gender.MALE,
                "CS", 2, 3.5, 0
        );
        Student s2 = new Student(
                "S2", "aliya", "123",
                "Aliya", "Serik", Gender.FEMALE,
                "IS", 1, 3.9, 0
        );
        Student s4 = new Student(
                "S4", "aidana", "123",
                "Aidana", "Nurlybek", Gender.FEMALE,
                "CS", 4, 3.7, 0
        );

        // PROFESSOR — автоматически activeResearcher = true
        Teacher t1 = new Teacher(
                "T1", "dias", "123",
                "Dias", "Tanirbergen", Gender.MALE,
                500000, "FIT", SchoolType.FIT, TeacherTitle.PROFESSOR
        );

        // LECTURER — activeResearcher = false по умолчанию
        Teacher t2 = new Teacher(
                "T2", "miras", "123",
                "Miras", "Ospanov", Gender.MALE,
                480000, "FIT", SchoolType.FIT, TeacherTitle.LECTURER
        );

        // НОВОЕ: сотрудник-исследователь — не Teacher, не Student, но Researcher
        ResearcherEmployee re1 = new ResearcherEmployee(
                "RE1", "zarina", "123",
                "Zarina", "Bekova", Gender.FEMALE,
                350000, "Research Lab", SchoolType.FIT
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

        // =========================
        // 2. PROFESSOR ALWAYS RESEARCHER TEST
        // =========================
        System.out.println("===== PROFESSOR ALWAYS RESEARCHER =====");

        System.out.println("t1 (PROFESSOR) isActiveResearcher: " + t1.isActiveResearcher());
        System.out.println("t2 (LECTURER)  isActiveResearcher: " + t2.isActiveResearcher());

        // Lecturer вручную становится researcher
        t2.becomeResearcher();
        System.out.println("t2 after becomeResearcher(): " + t2.isActiveResearcher());

        // Если повысить t2 до профессора — автоматически researcher
        Teacher t3 = new Teacher(
                "T3", "sara", "123",
                "Sara", "Nuova", Gender.FEMALE,
                460000, "FIT", SchoolType.FIT, TeacherTitle.SENIOR_LECTURER
        );
        System.out.println("t3 (SENIOR_LECTURER) isActiveResearcher: " + t3.isActiveResearcher());
        t3.setTitle(TeacherTitle.PROFESSOR);
        System.out.println("t3 after promotion to PROFESSOR: " + t3.isActiveResearcher());

        // =========================
        // 3. RESEARCHER EMPLOYEE TEST
        // =========================
        System.out.println();
        System.out.println("===== RESEARCHER EMPLOYEE (not Teacher, not Student) =====");

        ResearchPaper re1Paper = new ResearchPaper(
                "Deep Learning in Genomics",
                List.of("Zarina Bekova"),
                60,
                "Nature Machine Intelligence",
                22,
                LocalDate.of(2024, 11, 1),
                "10.1038/s42256-024-001"
        );

        re1.addResearchPaper(re1Paper);
        re1.setHIndex(4);

        System.out.println("ResearcherEmployee: " + re1);
        System.out.println("Papers:");
        re1.printPapers(new PaperByCitationsComparator());

        // ResearcherEmployee может участвовать в проекте
        ResearchProject project = new ResearchProject("Genomics AI");
        try {
            project.joinProject(re1);
            project.joinProject(t1);
            System.out.println("Project participants: " + project.getParticipants().size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // ResearcherEmployee в общем списке исследователей
        List<Researcher> allResearchers = new ArrayList<>();
        allResearchers.add(t1);
        allResearchers.add(s2);
        allResearchers.add(re1);

        System.out.println("Top cited among all researchers: " +
                ResearchUtils.getTopCitedResearcher(allResearchers).getResearcherName());

        // =========================
        // 4. ACADEMIC SYSTEM
        // =========================
        System.out.println();
        System.out.println("===== ACADEMIC SYSTEM =====");

        Course oopCourse = new Course("OOP", 5, "CS", 2);
        Course mathCourse = new Course("Math", 4, "Any", 1);

        oopCourse.registerStudent(s1);  // CS year 2 — OK
        oopCourse.registerStudent(s2);  // IS year 1 — BLOCKED wrong major
        oopCourse.registerStudent(s4);  // CS year 4 — BLOCKED wrong year

        mathCourse.registerStudent(s2); // IS year 1 — OK
        mathCourse.registerStudent(s1); // CS year 2 — BLOCKED wrong year

        oopCourse.addInstructor(t1);
        mathCourse.addInstructor(t2);

        oopCourse.addLesson(new Lesson(LessonType.LECTURE, "Introduction to OOP", 1));
        oopCourse.addLesson(new Lesson(LessonType.PRACTICE, "Classes and Objects", 2));

        t1.putMark(oopCourse, s1, new Mark(25, 25, 45));
        t2.putMark(mathCourse, s2, new Mark(20, 22, 40));

        oopCourse.printMarks();
        System.out.println();
        mathCourse.printMarks();
        System.out.println();

        ReportGenerator.generateCourseReport(oopCourse);

        // =========================
        // 5. STUDENT FEATURES
        // =========================
        System.out.println();
        System.out.println("===== STUDENT FEATURES =====");

        s1.viewCourses();
        System.out.println();
        s1.viewMarks();
        System.out.println();
        s1.viewTeachersOfCourse(oopCourse);
        System.out.println();
        s1.rateTeacher(t1, 5);
        s2.rateTeacher(t2, 4);

        Transcript transcript = new Transcript();
        transcript.addCourseMark(oopCourse, oopCourse.getMark(s1));
        transcript.printTranscript();

        // =========================
        // 6. TEACHER FEATURES
        // =========================
        System.out.println();
        System.out.println("===== TEACHER FEATURES =====");

        t1.viewCourses();
        System.out.println();
        t1.viewStudents(oopCourse);

        // =========================
        // 7. RESEARCH SYSTEM
        // =========================
        System.out.println();
        System.out.println("===== RESEARCH SYSTEM =====");

        ResearchPaper p1 = new ResearchPaper(
                "AI in Education",
                List.of("Dias Tanirbergen", "Aliya Serik"),
                25, "IEEE Access", 12,
                LocalDate.of(2024, 5, 10), "10.1000/182"
        );
        ResearchPaper p2 = new ResearchPaper(
                "Machine Learning for Students",
                List.of("Nurzhan Kalmurat"),
                40, "Springer Journal", 18,
                LocalDate.of(2025, 1, 15), "10.1000/183"
        );

        t1.addResearchPaper(p1);
        s2.addResearchPaper(p2);
        t1.setHIndex(5);
        s2.setHIndex(2);

        System.out.println("All papers sorted by citations:");
        ResearchUtils.printAllPapers(allResearchers, new PaperByCitationsComparator());
        System.out.println();

        System.out.println("All papers sorted by date:");
        ResearchUtils.printAllPapers(allResearchers, new PaperByDateComparator());
        System.out.println();

        System.out.println("All papers sorted by pages:");
        ResearchUtils.printAllPapers(allResearchers, new PaperByPagesComparator());
        System.out.println();

        System.out.println("Top cited overall: " +
                ResearchUtils.getTopCitedResearcher(allResearchers).getResearcherName());

        Researcher topBySchool = ResearchUtils.getTopCitedResearcherBySchool(
                allResearchers, SchoolType.FIT);
        if (topBySchool != null)
            System.out.println("Top in FIT: " + topBySchool.getResearcherName());

        Researcher topByYear = ResearchUtils.getTopCitedResearcherByYear(
                allResearchers, 2025);
        if (topByYear != null)
            System.out.println("Top in 2025: " + topByYear.getResearcherName());

        // =========================
        // 8. 4TH YEAR SUPERVISOR
        // =========================
        System.out.println();
        System.out.println("===== 4TH YEAR SUPERVISOR =====");

        try {
            s4.assignResearchSupervisor(t1); // h-index 5 — OK
            System.out.println("Supervisor assigned: " +
                    s4.getResearchSupervisor().getResearcherName());
        } catch (InvalidSupervisorException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            s4.assignResearchSupervisor(s2); // h-index 2 < 3 — EXCEPTION
        } catch (InvalidSupervisorException e) {
            System.out.println("Error (expected): " + e.getMessage());
        }

        // ResearcherEmployee как supervisor — h-index 4 >= 3 — OK
        try {
            s4.assignResearchSupervisor(re1);
            System.out.println("RE supervisor assigned: " +
                    s4.getResearchSupervisor().getResearcherName());
        } catch (InvalidSupervisorException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // =========================
        // 9. FAIL + CREDIT LIMIT
        // =========================
        System.out.println();
        System.out.println("===== FAIL LIMIT TEST =====");

        Student failedStudent = new Student(
                "S5", "failtest", "123",
                "Fail", "Student", Gender.MALE,
                "CS", 3, 2.0, 0
        );
        Course calc1 = new Course("Calculus 1", 3);
        Course calc2 = new Course("Calculus 2", 3);
        Course phys  = new Course("Physics", 3);
        Course algo  = new Course("Algorithms", 3);

        calc1.registerStudent(failedStudent);
        calc2.registerStudent(failedStudent);
        phys.registerStudent(failedStudent);

        calc1.putMark(failedStudent, new Mark(10, 10, 20));
        calc2.putMark(failedStudent, new Mark(10, 10, 15));
        phys.putMark(failedStudent,  new Mark(15, 10, 20));

        System.out.println("Failed count: " + failedStudent.getFailedCoursesCount());
        System.out.println("Can register: " + algo.registerStudent(failedStudent));

        System.out.println();
        System.out.println("===== CREDIT LIMIT TEST =====");

        Student richStudent = new Student(
                "S6", "rich", "123", "Rich", "Student",
                Gender.MALE, "CS", 1, 3.0, 0
        );
        Course c6 = new Course("Math6", 6);
        Course c7 = new Course("Phys6", 6);
        Course c8 = new Course("Chem6", 6);
        Course c9 = new Course("Bio6",  6);

        c6.registerStudent(richStudent); // 6
        c7.registerStudent(richStudent); // 12
        c8.registerStudent(richStudent); // 18
        boolean last = c9.registerStudent(richStudent); // 24 > 21 BLOCKED
        System.out.println("4th blocked: " + !last +
                " | Credits: " + richStudent.getRegisteredCredits());

        // =========================
        // 10. MANAGEMENT SYSTEM
        // =========================
        System.out.println();
        System.out.println("===== MANAGEMENT SYSTEM =====");

        AdminService adminService = new AdminService();
        adminService.addUser(s1);
        adminService.addUser(s2);
        adminService.addUser(s4);
        adminService.addUser(t1);
        adminService.addUser(t2);
        adminService.addUser(re1);
        adminService.addUser(manager);
        adminService.addUser(admin);

        adminService.updateUserName(s1, "Nurzhan", "Kalmurat-Updated");
        adminService.updateUserPassword(s2, "newpass456");

        User found = adminService.findUserById("RE1");
        if (found != null) System.out.println("Found: " + found.getFullName());

        adminService.printStudentsByGpa();
        System.out.println();
        adminService.printUsersSortedByName();
        System.out.println();
        adminService.printTeachers();

        ManagerService managerService = new ManagerService(manager);

        // НОВОЕ: студент отправляет заявку (раньше только Employee)
        Request studentRequest = new Request(s1, "Request for academic leave");
        managerService.submitRequest(studentRequest);
        managerService.approveRequest(studentRequest);

        Request teacherRequest = new Request(t1, "Approve academic mobility");
        managerService.submitRequest(teacherRequest);
        managerService.rejectRequest(teacherRequest);

        // НОВОЕ: студент отправляет сообщение учителю
        Message studentMsg = new Message(s1, t1, "When is the next deadline?");
        managerService.sendMessage(studentMsg);

        // Сообщение от менеджера учителю
        Message managerMsg = new Message(manager, t2, "Upload course materials today.");
        managerService.sendMessage(managerMsg);

        News news = new News("Midterm Week", "Midterm exams start next Monday.");
        news.attach(new NewsSubscriber("Student Portal"));
        news.attach(new NewsSubscriber("Teacher Portal"));
        news.attach(new NewsSubscriber("Mobile App"));
        managerService.publishNews(news);

        Course dbCourse = new Course("Databases", 4, "IS", 1);
        managerService.assignTeacherToCourse(t2, dbCourse);
        managerService.approveStudentRegistration(s2, dbCourse);

        List<users.User> allUsers = adminService.getUsers();
        managerService.printStudentsByGpa(allUsers);
        System.out.println();
        managerService.printUsersSortedByName(allUsers);
        System.out.println();
        managerService.printTeachersWithRating(allUsers);
        System.out.println();

        managerService.printRequests();
        System.out.println();
        managerService.printNews();
        System.out.println();
        managerService.printMessages();
        System.out.println();
        adminService.printLogs();
        System.out.println();
        managerService.printLogs();

        // =========================
        // 11. FINAL SYSTEM
        // =========================
        System.out.println();
        System.out.println("===== FINAL SYSTEM =====");

        DataStore dataStore = DataStore.getInstance();
        dataStore.addUser(s1);
        dataStore.addUser(s2);
        dataStore.addUser(s4);
        dataStore.addUser(t1);
        dataStore.addUser(re1);
        dataStore.addUser(manager);
        dataStore.addUser(admin);

        dataStore.save("data.ser");
        DataStore loadedData = DataStore.load("data.ser");

        AuthService authService = new AuthService();
        User loggedUser = authService.login("nurzhan", "123", loadedData.getUsers());
        if (loggedUser != null)
            System.out.println("Login success: " + loggedUser.getFullName());
        else
            System.out.println("Login failed");

        User factoryUser = UserFactory.createUser(
                "student", "S99", "john99", "pass123",
                "John", "Doe", Gender.MALE
        );
        factoryUser.setFirstName("Johnny");

        ResearchPaper factoryPaper = new ResearchPaper(
                "Factory Test Paper", List.of("John Doe"),
                5, "Test Journal", 8,
                LocalDate.of(2025, 3, 20), "10.9999/001"
        );
        ((Researcher) factoryUser).addResearchPaper(factoryPaper);
        System.out.println("Created via factory: " + factoryUser);
    }
}