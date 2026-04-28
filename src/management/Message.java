package management;

import java.time.LocalDateTime;
import users.User;

public class Message {
    // ИСПРАВЛЕНО: было Employee — теперь User,
    // чтобы студенты тоже могли отправлять сообщения
    private User sender;
    private User receiver;
    private String text;
    private LocalDateTime sentAt;

    public Message(User sender, User receiver, String text) {
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.sentAt = LocalDateTime.now();
    }

    public User getSender() { return sender; }

    public User getReceiver() { return receiver; }

    public String getText() { return text; }

    public LocalDateTime getSentAt() { return sentAt; }

    @Override
    public String toString() {
        return "Message{" +
                "sender=" + sender.getFullName() +
                ", receiver=" + receiver.getFullName() +
                ", text='" + text + '\'' +
                ", sentAt=" + sentAt +
                '}';
    }
}