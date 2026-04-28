package users;

import enums.Gender;
import enums.SchoolType;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import research.ResearchPaper;
import research.ResearchProject;
import research.Researcher;

/**
 * Сотрудник-исследователь — Employee, который является Researcher,
 * но не является ни Teacher, ни Student.
 * Требование: "there can be an employee who is neither Teacher nor Student, but IS a Researcher"
 */
public class ResearcherEmployee extends Employee implements Researcher {

    private int hIndex;
    private List<ResearchPaper> researchPapers = new ArrayList<>();
    private List<ResearchProject> researchProjects = new ArrayList<>();

    public ResearcherEmployee() {}

    public ResearcherEmployee(String id, String username, String password,
                               String firstName, String lastName, Gender gender,
                               double salary, String department, SchoolType school) {
        super(id, username, password, firstName, lastName, gender,
                salary, department, school);
    }

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
        return "ResearcherEmployee{" +
                "id='" + getId() + '\'' +
                ", name='" + getFullName() + '\'' +
                ", department='" + getDepartment() + '\'' +
                ", school=" + getSchool() +
                ", hIndex=" + hIndex +
                ", papers=" + researchPapers.size() +
                '}';
    }
}