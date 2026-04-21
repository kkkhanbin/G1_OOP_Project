package research;

import enums.SchoolType;
import exceptions.InvalidSupervisorException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ResearchUtils {

    public static void assignSupervisor(Researcher supervisor) throws InvalidSupervisorException {
        if (supervisor.getHIndex() < 3) {
            throw new InvalidSupervisorException("Supervisor must have h-index >= 3");
        }
    }

    public static void printAllPapers(List<Researcher> researchers, Comparator<ResearchPaper> comparator) {
        List<ResearchPaper> allPapers = new ArrayList<>();

        for (Researcher researcher : researchers) {
            allPapers.addAll(researcher.getResearchPapers());
        }

        allPapers.sort(comparator);

        for (ResearchPaper paper : allPapers) {
            System.out.println(paper);
        }
    }

    public static Researcher getTopCitedResearcher(List<Researcher> researchers) {
        Researcher best = null;
        int maxCitations = -1;

        for (Researcher researcher : researchers) {
            int totalCitations = 0;
            for (ResearchPaper paper : researcher.getResearchPapers()) {
                totalCitations += paper.getCitations();
            }

            if (totalCitations > maxCitations) {
                maxCitations = totalCitations;
                best = researcher;
            }
        }

        return best;
    }

    public static Researcher getTopCitedResearcherBySchool(List<Researcher> researchers, SchoolType school) {
        Researcher best = null;
        int maxCitations = -1;

        for (Researcher researcher : researchers) {
            if (researcher instanceof users.Employee employee) {
                if (employee.getSchool() != school) continue;

                int totalCitations = 0;
                for (ResearchPaper paper : researcher.getResearchPapers()) {
                    totalCitations += paper.getCitations();
                }

                if (totalCitations > maxCitations) {
                    maxCitations = totalCitations;
                    best = researcher;
                }
            }
        }

        return best;
    }

    public static Researcher getTopCitedResearcherByYear(List<Researcher> researchers, int year) {
        Researcher best = null;
        int maxCitations = -1;

        for (Researcher researcher : researchers) {
            int totalCitations = 0;

            for (ResearchPaper paper : researcher.getResearchPapers()) {
                if (paper.getPublicationDate().getYear() == year) {
                    totalCitations += paper.getCitations();
                }
            }

            if (totalCitations > maxCitations) {
                maxCitations = totalCitations;
                best = researcher;
            }
        }

        return best;
    }
}