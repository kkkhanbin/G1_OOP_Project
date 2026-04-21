package research;

import java.util.List;
import java.util.Comparator;

public interface Researcher {
    int getHIndex();
    void setHIndex(int hIndex);

    List<ResearchPaper> getResearchPapers();
    List<ResearchProject> getResearchProjects();

    void addResearchPaper(ResearchPaper paper);
    void addResearchProject(ResearchProject project);

    void printPapers(Comparator<ResearchPaper> comparator);
    String getResearcherName();
}