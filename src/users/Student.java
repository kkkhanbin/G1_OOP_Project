package users;

import enums.Gender;

public class Student extends User implements research.Researcher {
    private String major;
    private int yearOfStudy;
    private double gpa;
    private int credits;

    private int hIndex;
    private java.util.List<research.ResearchPaper> researchPapers = new java.util.ArrayList<>();
    private java.util.List<research.ResearchProject> researchProjects = new java.util.ArrayList<>();

    private research.Researcher researchSupervisor;

    public Student() {}

    public Student(String id, String username, String password,
                   String firstName, String lastName, Gender gender,
                   String major, int yearOfStudy, double gpa, int credits) {
        super(id, username, password, firstName, lastName, gender);
        this.major = major;
        this.yearOfStudy = yearOfStudy;
        this.gpa = gpa;
        this.credits = credits;
    }

    public String getMajor() {
        return major;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public double getGpa() {
        return gpa;
    }

    public int getCredits() {
        return credits;
    }

    public boolean canRegister(int newCredits) {
        return credits + newCredits <= 21;
    }

    public research.Researcher getResearchSupervisor() {
        return researchSupervisor;
    }

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

    @Override
    public int getHIndex() {
        return hIndex;
    }

    @Override
    public void setHIndex(int hIndex) {
        this.hIndex = hIndex;
    }

    @Override
    public java.util.List<research.ResearchPaper> getResearchPapers() {
        return researchPapers;
    }

    @Override
    public java.util.List<research.ResearchProject> getResearchProjects() {
        return researchProjects;
    }

    @Override
    public void addResearchPaper(research.ResearchPaper paper) {
        researchPapers.add(paper);
    }

    @Override
    public void addResearchProject(research.ResearchProject project) {
        researchProjects.add(project);
    }

    @Override
    public void printPapers(java.util.Comparator<research.ResearchPaper> comparator) {
        researchPapers.sort(comparator);
        for (research.ResearchPaper paper : researchPapers) {
            System.out.println(paper);
        }
    }

    @Override
    public String getResearcherName() {
        return getFullName();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + getId() + '\'' +
                ", name='" + getFullName() + '\'' +
                ", major='" + major + '\'' +
                ", yearOfStudy=" + yearOfStudy +
                ", gpa=" + gpa +
                ", credits=" + credits +
                ", researchSupervisor=" +
                (researchSupervisor == null ? "none" : researchSupervisor.getResearcherName()) +
                '}';
    }
}