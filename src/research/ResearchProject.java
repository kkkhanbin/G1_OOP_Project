package research;

import exceptions.NonResearcherJoinException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResearchProject implements Serializable {
    private String topic;
    private List<ResearchPaper> publishedPapers;
    private List<Researcher> participants;

    public ResearchProject(String topic) {
        this.topic = topic;
        this.publishedPapers = new ArrayList<>();
        this.participants = new ArrayList<>();
    }

    public String getTopic() {
        return topic;
    }

    public List<ResearchPaper> getPublishedPapers() {
        return publishedPapers;
    }

    public List<Researcher> getParticipants() {
        return participants;
    }

    public void addPaper(ResearchPaper paper) {
        publishedPapers.add(paper);
    }

    public void joinProject(Object person) throws NonResearcherJoinException {
        if (!(person instanceof Researcher researcher)) {
            throw new NonResearcherJoinException("Only researchers can join a research project");
        }
        participants.add(researcher);
    }

    @Override
    public String toString() {
        return "ResearchProject{" +
                "topic='" + topic + '\'' +
                ", publishedPapers=" + publishedPapers.size() +
                ", participants=" + participants.size() +
                '}';
    }
}