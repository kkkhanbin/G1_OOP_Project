package management;

import java.time.LocalDateTime;

public class News {
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

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publishedAt=" + publishedAt +
                '}';
    }
}