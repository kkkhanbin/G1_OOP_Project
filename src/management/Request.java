package management;

import users.Employee;
import java.time.LocalDateTime;

public class Request {
    private Employee sender;
    private String content;
    private boolean approved;
    private LocalDateTime createdAt;

    public Request(Employee sender, String content) {
        this.sender = sender;
        this.content = content;
        this.approved = false;
        this.createdAt = LocalDateTime.now();
    }

    public Employee getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public boolean isApproved() {
        return approved;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void approve() {
        this.approved = true;
    }

    @Override
    public String toString() {
        return "Request{" +
                "sender=" + sender.getFullName() +
                ", content='" + content + '\'' +
                ", approved=" + approved +
                ", createdAt=" + createdAt +
                '}';
    }
}