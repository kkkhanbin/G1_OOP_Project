package research;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResearchPaper {
    private String title;
    private List<String> authors;
    private int citations;
    private String journal;
    private int pages;
    private LocalDate publicationDate;
    private String doi;

    public ResearchPaper(String title, List<String> authors, int citations,
                         String journal, int pages, LocalDate publicationDate, String doi) {
        this.title = title;
        this.authors = new ArrayList<>(authors);
        this.citations = citations;
        this.journal = journal;
        this.pages = pages;
        this.publicationDate = publicationDate;
        this.doi = doi;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public int getCitations() {
        return citations;
    }

    public String getJournal() {
        return journal;
    }

    public int getPages() {
        return pages;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public String getDoi() {
        return doi;
    }

    @Override
    public String toString() {
        return "ResearchPaper{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", citations=" + citations +
                ", journal='" + journal + '\'' +
                ", pages=" + pages +
                ", publicationDate=" + publicationDate +
                ", doi='" + doi + '\'' +
                '}';
    }
}