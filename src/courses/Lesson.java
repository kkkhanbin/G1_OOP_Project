package courses;

import enums.LessonType;

public class Lesson implements java.io.Serializable {
    private LessonType type;
    private String topic;
    private int week;

    public Lesson(LessonType type, String topic, int week) {
        this.type = type;
        this.topic = topic;
        this.week = week;
    }

    public LessonType getType() {
        return type;
    }

    public String getTopic() {
        return topic;
    }

    public int getWeek() {
        return week;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "type=" + type +
                ", topic='" + topic + '\'' +
                ", week=" + week +
                '}';
    }
}