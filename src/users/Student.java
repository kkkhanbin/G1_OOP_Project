package users;

import courses.Course;
import courses.Mark;
import enums.Gender;
import java.util.ArrayList;
import java.util.List;

public class Student extends User implements research.Researcher {
    private String major;
    private int yearOfStudy;
    private double gpa;
    private int registeredCredits;
    private int failedCoursesCount;

    // НОВОЕ: студент теперь знает свои курсы и оценки
    private List<Course> registeredCourses = new ArrayList<>();

    private int hIndex;
    private List<research.ResearchPaper> researchPapers = new ArrayList<>();
    private List<research.ResearchProject> researchProjects = new ArrayList<>();

    private research.Researcher researchSupervisor;

    public Student() {}

    public Student(String id, String username, String password,
                   String firstName, String lastName, Gender gender,
                   String major, int yearOfStudy, double gpa, int initialCredits) {
        super(id, username, password, firstName, lastName, gender);
        this.major = major;
        this.yearOfStudy = yearOfStudy;
        this.gpa = gpa;
        this.registeredCredits = initialCredits;
        this.failedCoursesCount = 0;
    }

    public String getMajor() { return major; }

    public int getYearOfStudy() { return yearOfStudy; }

    public double getGpa() { return gpa; }

    public void setGpa(double gpa) { this.gpa = gpa; }

    public int getRegisteredCredits() { return registeredCredits; }

    public void addCredits(int credits) { this.registeredCredits += credits; }

    public int getFailedCoursesCount() { return failedCoursesCount; }

    public boolean canRegister(int newCredits) {
        return registeredCredits + newCredits <= 21;
    }

    public boolean canTakeMoreCourses() { return failedCoursesCount < 3; }

    public void incrementFailedCourses() { failedCoursesCount++; }

    // НОВОЕ: Course.registerStudent() вызывает этот метод чтобы студент знал свой курс
    public void addCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            registeredCourses.add(course);
        }
    }

    public List<Course> getRegisteredCourses() { return registeredCourses; }

    // НОВОЕ: студент видит все свои курсы
    public void viewCourses() {
        System.out.println("=== Courses of " + getFullName() + " ===");
        if (registeredCourses.isEmpty()) {
            System.out.println("  No courses registered.");
            return;
        }
        for (Course course : registeredCourses) {
            System.out.println("  - " + course.getName() +
                    " (" + course.getCredits() + " credits)");
        }
    }

    // НОВОЕ: студент видит все свои оценки
    public void viewMarks() {
        System.out.println("=== Marks of " + getFullName() + " ===");
        boolean hasAnyMark = false;
        for (Course course : registeredCourses) {
            Mark mark = course.getMark(this);
            if (mark != null) {
                hasAnyMark = true;
                System.out.println("  " + course.getName() + ": " + mark);
            }
        }
        if (!hasAnyMark) {
            System.out.println("  No marks yet.");
        }
    }

    // НОВОЕ: студент оценивает преподавателя (рейтинг 1-5)
    public void rateTeacher(Teacher teacher, int rating) {
        if (rating < 1 || rating > 5) {
            System.out.println("Rating must be between 1 and 5.");
            return;
        }
        teacher.addRating(rating);
        System.out.println(getFullName() + " rated " +
                teacher.getFullName() + ": " + rating + "/5");
    }

    // НОВОЕ: студент видит преподавателей конкретного курса
    public void viewTeachersOfCourse(Course course) {
        System.out.println("=== Teachers of " + course.getName() + " ===");
        if (course.getInstructors().isEmpty()) {
            System.out.println("  No instructors assigned.");
            return;
        }
        for (Teacher teacher : course.getInstructors()) {
            System.out.println("  - " + teacher.getFullName() +
                    " | " + teacher.getTitle() +
                    " | Rating: " + String.format("%.1f", teacher.getAverageRating()));
        }
    }

    public research.Researcher getResearchSupervisor() { return researchSupervisor; }

    public void assignResearchSupervisor(research.Researcher supervisor)
            throws exceptions.InvalidSupervisorException {

        if (yearOfStudy != 4) {
            System.out.println("Research supervisor is required only for 4th year students.");
            return;
        }

        if (supervisor.getHIndex() < 3) {
            throw new exceptions.InvalidSupervisorException("Supervisor must have h-index >= 3");
        }

        this.researchSupervisor = supervisor;
    }

    @Override public int getHIndex() { return hIndex; }
    @Override public void setHIndex(int hIndex) { this.hIndex = hIndex; }

    @Override
    public List<research.ResearchPaper> getResearchPapers() { return researchPapers; }

    @Override
    public List<research.ResearchProject> getResearchProjects() { return researchProjects; }

    @Override
    public void addResearchPaper(research.ResearchPaper paper) { researchPapers.add(paper); }

    @Override
    public void addResearchProject(research.ResearchProject project) { researchProjects.add(project); }

    @Override
    public void printPapers(java.util.Comparator<research.ResearchPaper> comparator) {
        researchPapers.sort(comparator);
        for (research.ResearchPaper paper : researchPapers) {
            System.out.println(paper);
        }
    }

    @Override
    public String getResearcherName() { return getFullName(); }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + getId() + '\'' +
                ", name='" + getFullName() + '\'' +
                ", major='" + major + '\'' +
                ", yearOfStudy=" + yearOfStudy +
                ", gpa=" + gpa +
                ", registeredCredits=" + registeredCredits +
                ", failedCoursesCount=" + failedCoursesCount +
                ", researchSupervisor=" +
                (researchSupervisor == null ? "none" : researchSupervisor.getResearcherName()) +
                '}';
    }
}