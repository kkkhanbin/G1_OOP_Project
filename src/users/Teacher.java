package users;

import enums.Gender;
import enums.TeacherTitle;

public class Teacher extends Employee implements research.Researcher {
    private TeacherTitle title;

    private int hIndex;
    private java.util.List<research.ResearchPaper> researchPapers = new java.util.ArrayList<>();
    private java.util.List<research.ResearchProject> researchProjects = new java.util.ArrayList<>();

    public Teacher() {}

    public Teacher(String id, String username, String password,
                   String firstName, String lastName, Gender gender,
                   double salary, String department, TeacherTitle title) {
        super(id, username, password, firstName, lastName, gender, salary, department);
        this.title = title;
    }

    public TeacherTitle getTitle() {
        return title;
    }

    public void setTitle(TeacherTitle title) {
        this.title = title;
    }

    public boolean isProfessor() {
        return title == TeacherTitle.PROFESSOR;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + getId() + '\'' +
                ", name='" + getFullName() + '\'' +
                ", department='" + getDepartment() + '\'' +
                ", title=" + title +
                '}';
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

}