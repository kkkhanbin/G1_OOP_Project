package management;

import users.Employee;
import java.time.LocalDateTime;

public class Message {
    private Employee sender;
    private Employee receiver;
    private String text;
    private LocalDateTime sentAt;

    public Message(Employee sender, Employee receiver, String text) {
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.sentAt = LocalDateTime.now();
    }

    public Employee getSender() {
        return sender;
    }

    public Employee getReceiver() {
        return receiver;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

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