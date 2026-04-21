package logs;

import java.time.LocalDateTime;

public class LogRecord {
    private String action;
    private LocalDateTime timestamp;

    public LogRecord(String action) {
        this.action = action;
        this.timestamp = LocalDateTime.now();
    }

    public String getAction() {
        return action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "LogRecord{" +
                "action='" + action + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}