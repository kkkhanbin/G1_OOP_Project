package users;

import courses.Course;
import courses.Mark;
import enums.Gender;
import enums.SchoolType;
import enums.TeacherTitle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import research.ResearchPaper;
import research.ResearchProject;
import research.Researcher;

public class Teacher extends Employee implements Researcher {
    private TeacherTitle title;

    /**
     * Флаг активного исследователя.
     * Для PROFESSOR всегда true — нельзя отключить.
     * Для остальных — по желанию (non-professors CAN be researchers).
     */
    private boolean activeResearcher;

    private List<Course> courses = new ArrayList<>();
    private List<Integer> ratings = new ArrayList<>();

    private int hIndex;
    private List<ResearchPaper> researchPapers = new ArrayList<>();
    private List<ResearchProject> researchProjects = new ArrayList<>();

    public Teacher() {}

    public Teacher(String id, String username, String password,
                   String firstName, String lastName, Gender gender,
                   double salary, String department, SchoolType school,
                   TeacherTitle title) {
        super(id, username, password, firstName, lastName, gender,
                salary, department, school);
        this.title = title;
        // Профессор — ВСЕГДА исследователь автоматически
        this.activeResearcher = (title == TeacherTitle.PROFESSOR);
    }

    public TeacherTitle getTitle() { return title; }

    /**
     * Сменить звание.
     * Если повышаем до профессора — автоматически становится исследователем.
     * Если понижаем с профессора — статус researcher сохраняется (уже накоплены труды).
     */
    public void setTitle(TeacherTitle title) {
        this.title = title;
        if (title == TeacherTitle.PROFESSOR) {
            this.activeResearcher = true; // профессор обязан быть researcher
        }
    }

    public boolean isProfessor() { return title == TeacherTitle.PROFESSOR; }

    /**
     * Является ли учитель активным исследователем.
     * Профессора — всегда true.
     * Остальные — могут включить вручную через becomeResearcher().
     */
    public boolean isActiveResearcher() { return activeResearcher; }

    /**
     * Для не-профессоров: явно заявить что преподаватель также является Researcher.
     */
    public void becomeResearcher() {
        this.activeResearcher = true;
    }

    // ========== COURSE METHODS ==========

    public void addCourse(Course course) {
        if (!courses.contains(course)) courses.add(course);
    }

    public List<Course> getCourses() { return courses; }

    public void viewCourses() {
        System.out.println("=== Courses of " + getFullName() + " ===");
        if (courses.isEmpty()) {
            System.out.println("  No courses assigned.");
            return;
        }
        for (Course course : courses) {
            System.out.println("  - " + course.getName() +
                    " (" + course.getCredits() + " credits)" +
                    " | Students: " + course.getStudents().size());
        }
    }

    public void viewStudents(Course course) {
        if (!courses.contains(course)) {
            System.out.println("You are not teaching: " + course.getName());
            return;
        }
        System.out.println("=== Students in " + course.getName() + " ===");
        if (course.getStudents().isEmpty()) {
            System.out.println("  No students registered.");
            return;
        }
        for (Student student : course.getStudents()) {
            Mark mark = course.getMark(student);
            System.out.println("  - " + student.getFullName() +
                    " | GPA: " + student.getGpa() +
                    " | Mark: " + (mark != null ? mark.getLetterGrade() : "not graded"));
        }
    }

    public void putMark(Course course, Student student, Mark mark) {
        if (!courses.contains(course)) {
            System.out.println("You are not teaching: " + course.getName());
            return;
        }
        course.putMark(student, mark);
        System.out.println(getFullName() + " graded " + student.getFullName() +
                " in " + course.getName() + ": " + mark.getLetterGrade());
    }

    // ========== RATING METHODS ==========

    public void addRating(int rating) { ratings.add(rating); }

    public double getAverageRating() {
        if (ratings.isEmpty()) return 0.0;
        int sum = 0;
        for (int r : ratings) sum += r;
        return (double) sum / ratings.size();
    }

    // ========== RESEARCHER METHODS ==========

    @Override
    public int getHIndex() { return hIndex; }

    @Override
    public void setHIndex(int hIndex) { this.hIndex = hIndex; }

    @Override
    public List<ResearchPaper> getResearchPapers() { return researchPapers; }

    @Override
    public List<ResearchProject> getResearchProjects() { return researchProjects; }

    @Override
    public void addResearchPaper(ResearchPaper paper) { researchPapers.add(paper); }

    @Override
    public void addResearchProject(ResearchProject project) { researchProjects.add(project); }

    @Override
    public void printPapers(Comparator<ResearchPaper> comparator) {
        researchPapers.sort(comparator);
        for (ResearchPaper paper : researchPapers) {
            System.out.println(paper);
        }
    }

    @Override
    public String getResearcherName() { return getFullName(); }

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + getId() + '\'' +
                ", name='" + getFullName() + '\'' +
                ", department='" + getDepartment() + '\'' +
                ", school=" + getSchool() +
                ", title=" + title +
                ", activeResearcher=" + activeResearcher +
                ", rating=" + String.format("%.1f", getAverageRating()) +
                '}';
    }
}