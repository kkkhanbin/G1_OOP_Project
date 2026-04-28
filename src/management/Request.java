package management;

import java.time.LocalDateTime;
import users.User;

public class Request {
    private User sender;       // ИСПРАВЛЕНО: было Employee, теперь User — студенты тоже могут подавать заявки
    private String content;
    private boolean approved;
    private boolean rejected;
    private LocalDateTime createdAt;

    public Request(User sender, String content) {
        this.sender = sender;
        this.content = content;
        this.approved = false;
        this.rejected = false;
        this.createdAt = LocalDateTime.now();
    }

    public User getSender() { return sender; }

    public String getContent() { return content; }

    public boolean isApproved() { return approved; }

    public boolean isRejected() { return rejected; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public void approve() { this.approved = true; }

    // НОВОЕ: отклонить заявку
    public void reject() { this.rejected = true; }

    public String getStatus() {
        if (approved) return "APPROVED";
        if (rejected) return "REJECTED";
        return "PENDING";
    }

    @Override
    public String toString() {
        return "Request{" +
                "sender=" + sender.getFullName() +
                ", content='" + content + '\'' +
                ", status=" + getStatus() +
                ", createdAt=" + createdAt +
                '}';
    }
}