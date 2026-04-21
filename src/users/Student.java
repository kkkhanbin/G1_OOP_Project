package users;

import enums.Gender;

public class Student extends User implements research.Researcher{
    private String major;
    private int yearOfStudy;
    private double gpa;
    private int credits;
    
    private int hIndex;
    private java.util.List<research.ResearchPaper> researchPapers = new java.util.ArrayList<>();
    private java.util.List<research.ResearchProject> researchProjects = new java.util.ArrayList<>();
    
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

    public boolean canRegister(int newCredits) {
        return credits + newCredits <= 21;
    }

    //getters

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




    @Override
    public String toString() {
        return "Student{" +
                "id='" + getId() + '\'' +
                ", name='" + getFullName() + '\'' +
                ", major='" + major + '\'' +
                ", yearOfStudy=" + yearOfStudy +
                ", gpa=" + gpa +
                ", credits=" + credits +
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