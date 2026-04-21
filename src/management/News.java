package management;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class News {
    private List<Observer> observers = new ArrayList<>();
    private String title;
    private String content;
    private LocalDateTime publishedAt;

    
    public News(String title, String content) {
        this.title = title;
        this.content = content;
        this.publishedAt = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publishedAt=" + publishedAt +
                '}';
    }
}